package com.sparta.ticketing.service.board;

import com.sparta.ticketing.dto.board.BoardRequest;
import com.sparta.ticketing.dto.board.BoardResponse;
import com.sparta.ticketing.entity.Board;
import org.springframework.stereotype.Component;

@Component
public interface BoardConnectorInterface {
    Board addBoard(BoardRequest boardRequest, Long id);

    Board findById(Long boardId);

    Board updateBoard(BoardRequest boardRequest, Long id);

    void deleteBoard(Long boardId, Long userId);
}
