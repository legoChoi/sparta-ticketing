package com.sparta.ticketing.service.comment_like;

import com.sparta.ticketing.dto.comment_like.CommentLikeRequest;
import com.sparta.ticketing.entity.CommentLike;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentLikeConnectorInterface {
    CommentLike addCommentLike(CommentLikeRequest commentLikeRequest, Long id);

    List<CommentLike> findAllByUserId(Long id);

    List<CommentLike> findAllByCommentId(Long commentId);

    void deleteCommentLike(Long commentLikeId, Long id);
}
