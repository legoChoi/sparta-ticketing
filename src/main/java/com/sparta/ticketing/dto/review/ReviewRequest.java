package com.sparta.ticketing.dto.review;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequest {

    @NotNull
    private final Long sessionId;

    @NotNull
    private final String content;

    @NotNull
    @Min(1)
    @Max(5)
    private final int star;

    public ReviewRequest(Long sessionId, String content, int star) {
        this.sessionId = sessionId;
        this.content = content;
        this.star = star;
    }

    @JsonCreator
    public static ReviewRequest from(
            @JsonProperty("sessionId") Long sessionId,
            @JsonProperty("content") String content,
            @JsonProperty("star") int star
    ) {
        return new ReviewRequest(sessionId, content, star);
    }
}
