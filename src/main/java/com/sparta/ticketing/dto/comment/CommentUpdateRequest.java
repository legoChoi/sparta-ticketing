package com.sparta.ticketing.dto.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommentUpdateRequest {
    private Long boardId;
    private String contents;

    public CommentUpdateRequest(Long boardId, String contents) {
        this.boardId = boardId;
        this.contents = contents;
    }

    @JsonCreator
    public static CommentUpdateRequest from(
            @JsonProperty("boardId") Long boardId,
            @JsonProperty("contents") String contents
            ) {
        return new CommentUpdateRequest(boardId, contents);
    }
}
