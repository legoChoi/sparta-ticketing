package com.sparta.ticketing.controller.review;

import com.sparta.ticketing.dto.review.ReviewListResponse;
import com.sparta.ticketing.dto.review.ReviewRequest;
import com.sparta.ticketing.dto.review.ReviewResponse;
import com.sparta.ticketing.dto.review.ReviewUpdateRequest;
import com.sparta.ticketing.service.review.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(
            @Valid @RequestBody ReviewRequest reviewRequest,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");

        ReviewResponse reviewResponse = reviewService.addReview(reviewRequest, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }

    @GetMapping
    public ResponseEntity<ReviewListResponse> getMyReview(HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        List<ReviewResponse> reviewResponses = reviewService.getMyReview(id);
        ReviewListResponse reviewListResponse = ReviewListResponse.from(reviewResponses);
        return ResponseEntity.status(HttpStatus.OK).body(reviewListResponse);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<ReviewListResponse> getBySession(@PathVariable long sessionId) {
        List<ReviewResponse> reviewResponses = reviewService.getMySession(sessionId);
        ReviewListResponse reviewListResponse = ReviewListResponse.from(reviewResponses);
        return ResponseEntity.status(HttpStatus.OK).body(reviewListResponse);
    }

    @PatchMapping
    public ResponseEntity<ReviewResponse> updateReview(
            @Valid @RequestBody ReviewUpdateRequest reviewUpdateRequest,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        ReviewResponse reviewResponse = reviewService.updateReview(reviewUpdateRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(reviewResponse);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId, HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        reviewService.deleteReview(reviewId, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
