package com.sparta.ticketing.service.board;

import com.sparta.ticketing.dto.board.BoardRequest;
import com.sparta.ticketing.dto.board.BoardUpdateRequest;
import com.sparta.ticketing.entity.Board;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardConnectorInterface {
    Board addBoard(BoardRequest boardRequest, Long id);

    Board findById(Long boardId);

    Board updateBoard(BoardUpdateRequest boardRequest, Long id);

    void deleteBoard(Long boardId, Long userId);

    List<Board> findAll();
}
