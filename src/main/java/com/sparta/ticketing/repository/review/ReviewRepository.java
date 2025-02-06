package com.sparta.ticketing.repository.review;

import com.sparta.ticketing.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByUserId(Long id);

    List<Review> findAllBySessionId(long sessionId);
}
