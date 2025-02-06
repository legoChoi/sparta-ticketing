package com.sparta.ticketing.dto.comment_like;

import com.sparta.ticketing.entity.CommentLike;
import lombok.Getter;

@Getter
public class CommentLikeResponse {

    private final Long commentLikeId;
    private final Long commentId;

    public CommentLikeResponse(Long commentLikeId, Long commentId) {
        this.commentLikeId = commentLikeId;
        this.commentId = commentId;
    }

    public static CommentLikeResponse from(CommentLike commentLike) {
        return new CommentLikeResponse(
                commentLike.getId(),
                commentLike.getComment().getId());
    }
}
