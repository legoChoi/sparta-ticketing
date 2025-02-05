package com.sparta.ticketing.dto.boardLike;

import com.sparta.ticketing.entity.BoardLike;
import lombok.Getter;

@Getter
public class BoardLikeResponse {
    private final Long boardLikeId;
    private final Long boardId;

    public BoardLikeResponse(Long boardLikeId, Long boardId) {
        this.boardLikeId = boardLikeId;
        this.boardId = boardId;
    }

    public static BoardLikeResponse from(BoardLike board) {
        return new BoardLikeResponse(board.getId(),board.getBoard().getId());
    }
}
