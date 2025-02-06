package com.sparta.ticketing.service.comment_like;

import com.sparta.ticketing.dto.comment_like.CommentLikeRequest;
import com.sparta.ticketing.dto.comment_like.CommentLikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentLikeService {
    public CommentLikeResponse addCommentLike(CommentLikeRequest commentLikeRequest, Long id) {
        return null;
    }

    public List<CommentLikeResponse> getAllByUserId(Long id) {
        return null;
    }

    public List<CommentLikeResponse> getAllByCommentId(Long commentId) {
        return null;
    }

    public void deleteCommentLike(Long commentLikeId, Long id) {

    }
}
