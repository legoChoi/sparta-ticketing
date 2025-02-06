package com.sparta.ticketing.dto.review;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewListResponse {

    private final List<ReviewResponse> reviewResponses;

    public ReviewListResponse(List<ReviewResponse> reviewResponses) {
        this.reviewResponses = reviewResponses;
    }

    public static ReviewListResponse from(List<ReviewResponse> reviewResponses) {
        return new ReviewListResponse(reviewResponses);
    }

}
