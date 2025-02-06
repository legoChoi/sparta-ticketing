package com.sparta.ticketing.repository.board_like;

import com.sparta.ticketing.dto.boardLike.BoardLikeRequest;
import com.sparta.ticketing.entity.Board;
import com.sparta.ticketing.entity.BoardLike;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.exception.InsufficientPermissionException;
import com.sparta.ticketing.service.board.BoardConnectorInterface;
import com.sparta.ticketing.service.board_like.BoardLikeConnectorInterface;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardLikeConnectorInterfaceImpl implements BoardLikeConnectorInterface {
    private final BoardLikeRepository boardLikeRepository;
    private final UserConnectInterface userConnectInterface;
    private final BoardConnectorInterface boardConnectorInterface;

    @Override
    @Transactional
    public BoardLike addBoardLike(BoardLikeRequest boardLikeRequest, Long id) {
        User user = userConnectInterface.findById(id);
        Board board = boardConnectorInterface.findById(boardLikeRequest.getBoardId());
        BoardLike boardLike = BoardLike.from(user, board);
        return boardLikeRepository.save(boardLike);
    }

    @Override
    public List<BoardLike> findAllByBoardId(Long boardId) {
        return boardLikeRepository.findAllByBoardId(boardId);
    }

    @Override
    public List<BoardLike> findAllByUserId(Long id) {
        return boardLikeRepository.findAllByUserId(id);
    }

    @Override
    @Transactional
    public void deleteBoardLike(Long boardLikeId, Long userId) {
        BoardLike boardLike = findById(boardLikeId);
        if (!boardLike.getUser().getId().equals(userId)) {
            throw new InsufficientPermissionException(ExceptionStatus.UNAUTHORIZED_ACCESS.getMessage());
        }
        boardLikeRepository.delete(boardLike);
    }

    private BoardLike findById(Long boardLikeId) {
        return boardLikeRepository.findById(boardLikeId)
                .orElseThrow(()->new IllegalArgumentException(ExceptionStatus.NOTFOUND_LIKE.getMessage()));
    }
}
