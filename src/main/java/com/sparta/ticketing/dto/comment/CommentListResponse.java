package com.sparta.ticketing.dto.comment;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponse {
    private final List<CommentResponse> commentResponses;

    public CommentListResponse(List<CommentResponse> commentResponses) {
        this.commentResponses = commentResponses;
    }

    public static CommentListResponse from(List<CommentResponse> commentResponses) {
        return new CommentListResponse(commentResponses);
    }
}
