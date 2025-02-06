package com.sparta.ticketing.dto.comment_like;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CommentLikeRequest {
    private final Long commentId;

    public CommentLikeRequest(Long commentId) {
        this.commentId = commentId;
    }

    @JsonCreator
    public static CommentLikeRequest from(@JsonProperty("commentId") Long commentId) {
        return new CommentLikeRequest(commentId);
    }
}
