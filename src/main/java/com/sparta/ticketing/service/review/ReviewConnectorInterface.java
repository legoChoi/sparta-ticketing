package com.sparta.ticketing.service.review;

import com.sparta.ticketing.dto.review.ReviewRequest;
import com.sparta.ticketing.entity.Review;
import org.springframework.stereotype.Component;

@Component
public interface ReviewConnectorInterface {
    Review addReview(ReviewRequest reviewRequest, Long id);
}
