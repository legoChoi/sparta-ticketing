package com.sparta.ticketing.service.review;

import com.sparta.ticketing.dto.review.ReviewRequest;
import com.sparta.ticketing.dto.review.ReviewResponse;
import com.sparta.ticketing.dto.review.ReviewUpdateRequest;
import com.sparta.ticketing.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewConnectorInterface reviewConnectorInterface;

    public ReviewResponse addReview(ReviewRequest reviewRequest, Long id) {
        Review review = reviewConnectorInterface.addReview(reviewRequest, id);
        return ReviewResponse.from(review);
    }

    public List<ReviewResponse> getMyReview(Long id) {
        List<Review> reviews = reviewConnectorInterface.findAllByUserId(id);
        return reviews.stream().map(ReviewResponse::from).toList();
    }

    public List<ReviewResponse> getMySession(long sessionId) {
        List<Review> reviews = reviewConnectorInterface.findAllBySessionId(sessionId);
        return reviews.stream().map(ReviewResponse::from).toList();
    }

    public ReviewResponse updateReview(ReviewUpdateRequest reviewUpdateRequest, Long id) {
        Review review = reviewConnectorInterface.updateReview(reviewUpdateRequest, id);
        return ReviewResponse.from(review);
    }

    public void deleteReview(Long reviewId, Long id) {
        reviewConnectorInterface.deleteReview(reviewId, id);
    }
}
