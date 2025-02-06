package com.sparta.ticketing.repository.review;

import com.sparta.ticketing.dto.review.ReviewRequest;
import com.sparta.ticketing.dto.review.ReviewUpdateRequest;
import com.sparta.ticketing.entity.Review;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.service.review.ReviewConnectorInterface;
import com.sparta.ticketing.service.session.SessionConnectorInterface;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReviewConnectorInterfaceImpl implements ReviewConnectorInterface {

    private final ReviewRepository reviewRepository;

    private final UserConnectInterface userConnectInterface;
    private final SessionConnectorInterface sessionConnectorInterface;

    @Override
    @Transactional
    public Review addReview(ReviewRequest reviewRequest, Long id) {
        User user = userConnectInterface.findById(id);
        Session session = sessionConnectorInterface.findById(reviewRequest.getSessionId());
        Review review = Review.from(
                user,
                session,
                reviewRequest.getContent(),
                reviewRequest.getStar());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findAllByUserId(Long id) {
        return reviewRepository.findAllByUserId(id);
    }

    @Override
    public List<Review> findAllBySessionId(long sessionId) {
        return reviewRepository.findAllBySessionId(sessionId);
    }

    @Override
    @Transactional
    public Review updateReview(ReviewUpdateRequest reviewUpdateRequest, Long id) {
        Review review = findById(reviewUpdateRequest.getReviewId());
        if (!review.getUser().getId().equals(id)) {
            throw new RuntimeException("해당 리뷰에 권한없음");
        }
        if (!review.getContents().equals(reviewUpdateRequest.getContent())) {
            review.updateContents(reviewUpdateRequest.getContent());
        }

        if (review.getStar() != reviewUpdateRequest.getStar()) {
            review.updateStar(reviewUpdateRequest.getStar());
        }

        return review;
    }

    @Override
    public void deleteReview(Long reviewId, Long id) {
        Review review = findById(reviewId);
        if (!review.getUser().getId().equals(id)) {
            throw new RuntimeException("리뷰에 권한없음");
        }
        reviewRepository.delete(review);
    }

    private Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(()->new IllegalArgumentException("리뷰를 찾을수 없음"));
    }
}
