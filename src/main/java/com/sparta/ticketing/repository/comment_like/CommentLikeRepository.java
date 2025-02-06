package com.sparta.ticketing.repository.comment_like;

import com.sparta.ticketing.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    List<CommentLike> findAllByUserId(Long id);

    List<CommentLike> findAllByCommentId(Long commentId);
}
