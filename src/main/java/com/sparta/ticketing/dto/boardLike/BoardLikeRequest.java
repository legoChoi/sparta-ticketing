package com.sparta.ticketing.dto.boardLike;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class BoardLikeRequest {

    @NotNull
    private final Long boardId;

    public BoardLikeRequest(Long boardId) {
        this.boardId = boardId;
    }

    @JsonCreator
    public static BoardLikeRequest from(
            @JsonProperty("boardId") Long boardId
    ) {
        return new BoardLikeRequest(boardId);
    }
}
