package com.sparta.ticketing.service.board;

import com.sparta.ticketing.dto.board.BoardRequest;
import com.sparta.ticketing.dto.board.BoardResponse;
import com.sparta.ticketing.dto.board.BoardUpdateRequest;
import com.sparta.ticketing.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardConnectorInterface boardConnectorInterface;

    public BoardResponse addBoard(BoardRequest boardRequest, Long id) {
        Board board = boardConnectorInterface.addBoard(boardRequest,id);
        return BoardResponse.from(board);
    }

    public BoardResponse getBoard(Long boardId) {
        Board board = boardConnectorInterface.findById(boardId);
        return BoardResponse.from(board);
    }

    public BoardResponse updateBoard(BoardUpdateRequest boardRequest, Long id) {
        Board board = boardConnectorInterface.updateBoard(boardRequest,id);
        return BoardResponse.from(board);
    }


    public void deleteBoard(Long boardId, Long userId) {
        boardConnectorInterface.deleteBoard(boardId, userId);
    }
}
