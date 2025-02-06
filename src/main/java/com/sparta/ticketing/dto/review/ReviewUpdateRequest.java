package com.sparta.ticketing.dto.review;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReviewUpdateRequest {

    private final Long reviewId;

    private final Long sessionId;

    private final String content;

    private final int star;

    public ReviewUpdateRequest(Long reviewId, Long sessionId, String content, int star) {
        this.reviewId = reviewId;
        this.sessionId = sessionId;
        this.content = content;
        this.star = star;
    }

    @JsonCreator
    public static ReviewUpdateRequest from(
            @JsonProperty("reviewId") Long reviewId,
            @JsonProperty("sessionId") Long sessionId,
            @JsonProperty("content") String content,
            @JsonProperty("star") int star
    ) {
        return new ReviewUpdateRequest(reviewId, sessionId, content, star);
    }
}
