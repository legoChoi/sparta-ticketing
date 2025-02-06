package com.sparta.ticketing.dto.review;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewUpdateRequest {

    @NotNull
    private final Long reviewId;

    @NotNull
    private final Long sessionId;

    @NotNull
    private final String content;

    @NotNull
    @Min(1)
    @Max(5)
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
