package com.sparta.ticketing.dto.comment;

import java.util.List;

public class CommentListResponse {
    public static CommentListResponse from(List<CommentResponse> commentResponses) {
        return new CommentListResponse();
    }
}
