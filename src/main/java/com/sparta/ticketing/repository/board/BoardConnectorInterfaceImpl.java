package com.sparta.ticketing.repository.board;

import com.sparta.ticketing.dto.board.BoardRequest;
import com.sparta.ticketing.dto.board.BoardUpdateRequest;
import com.sparta.ticketing.entity.Board;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.exception.InsufficientPermissionException;
import com.sparta.ticketing.service.board.BoardConnectorInterface;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardConnectorInterfaceImpl implements BoardConnectorInterface {

    private final BoardRepository boardRepository;
    private final UserConnectInterface userConnectInterface;

    @Override
    @Transactional
    public Board addBoard(BoardRequest boardRequest, Long id) {
        User user = userConnectInterface.findById(id);
        Board board = Board.from(boardRequest, user);
        return boardRepository.save(board);
    }

    @Override
    public Board findById(Long boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException(ExceptionStatus.NOTFOUND_BOARD.getMessage()));
    }

    @Override
    @Transactional
    public Board updateBoard(BoardUpdateRequest boardRequest, Long id) {
        Board board = getBoard(boardRequest.getBoardId(), id);

        if (!board.getTitle().equals(boardRequest.getTitle())) {
            board.updateTitle(boardRequest.getTitle());
        }

        if (!board.getContents().equals(boardRequest.getContents())) {
            board.updateContents(boardRequest.getContents());
        }

        return board;
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardId, Long userId) {
        Board board = getBoard(boardId, boardId);
        boardRepository.delete(board);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    private Board getBoard(long boardId, Long id) {
        User user = userConnectInterface.findById(id);
        Board board = findById(boardId);
        if (!board.getId().equals(user.getId())) {
            throw new InsufficientPermissionException
                    (ExceptionStatus.UNAUTHORIZED_ACCESS.getMessage());
        }

        return board;
    }
}
