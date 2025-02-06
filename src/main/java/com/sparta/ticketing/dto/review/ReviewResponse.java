package com.sparta.ticketing.dto.review;

import com.sparta.ticketing.entity.Review;
import com.sparta.ticketing.entity.Session;
import lombok.Getter;

@Getter
public class ReviewResponse {

    private final Long id;

    private final Session session;

    private final String content;

    private final int star;

    public ReviewResponse(Long id, Session session, String content, int star) {
        this.id = id;
        this.session = session;
        this.content = content;
        this.star = star;
    }

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getId(),
                review.getSession(),
                review.getContents(),
                review.getStar());
    }
}
