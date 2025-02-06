package com.sparta.ticketing.service.board_like;

import com.sparta.ticketing.dto.boardLike.BoardLikeRequest;
import com.sparta.ticketing.entity.Board;
import com.sparta.ticketing.entity.BoardLike;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BoardLikeConnectorInterface {
    BoardLike addBoardLike(BoardLikeRequest boardLikeRequest, Long id);

    List<BoardLike> findAllByBoardId(Long boardId);

    List<BoardLike> findAllByUserId(Long id);

    void deleteBoardLike(Long boardLikeId, Long userId);
}
