package com.sparta.ticketing.dto.board;

import lombok.Getter;

import java.util.List;

@Getter
public class BoardListResponse {
    private final List<BoardResponse> boardResponses;

    public BoardListResponse(List<BoardResponse> boardResponses) {
        this.boardResponses = boardResponses;
    }

    public static BoardListResponse from(List<BoardResponse> boardResponses) {
        return new BoardListResponse(boardResponses);
    }
}
