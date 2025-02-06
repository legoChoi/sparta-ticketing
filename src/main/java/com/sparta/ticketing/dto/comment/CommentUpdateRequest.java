package com.sparta.ticketing.dto.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentUpdateRequest {
    @NotNull
    private final Long commentId;

    @NotNull
    private final Long boardId;

    @NotNull
    private final String contents;

    public CommentUpdateRequest(Long commentId, Long boardId, String contents) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.contents = contents;
    }

    @JsonCreator
    public static CommentUpdateRequest from(
            @JsonProperty("commentId") Long commentId,
            @JsonProperty("boardId") Long boardId,
            @JsonProperty("contents") String contents
            ) {
        return new CommentUpdateRequest(commentId, boardId, contents);
    }
}
