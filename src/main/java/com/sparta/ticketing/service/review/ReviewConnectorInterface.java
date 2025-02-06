package com.sparta.ticketing.service.review;

import com.sparta.ticketing.dto.review.ReviewRequest;
import com.sparta.ticketing.dto.review.ReviewUpdateRequest;
import com.sparta.ticketing.entity.Review;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewConnectorInterface {
    Review addReview(ReviewRequest reviewRequest, Long id);

    List<Review> findAllByUserId(Long id);

    List<Review> findAllBySessionId(long sessionId);

    Review updateReview(ReviewUpdateRequest reviewUpdateRequest, Long id);

    void deleteReview(Long reviewId, Long id);
}
