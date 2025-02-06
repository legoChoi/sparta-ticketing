package com.sparta.ticketing.dto.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequest {
    @NotNull
    private final Long boardId;

    @NotNull
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
