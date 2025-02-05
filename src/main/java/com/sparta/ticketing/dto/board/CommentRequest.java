package com.sparta.ticketing.dto.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommentRequest {
    private final Long boardId;
    private final String contents;

    public CommentRequest(Long boardId, String contents) {
        this.boardId = boardId;
        this.contents = contents;
    }

    @JsonCreator
    public static CommentRequest from(
            @JsonProperty("boardId") Long boardId,
            @JsonProperty("contents") String contents
    ) {
        return new CommentRequest(boardId, contents);
    }
}
