package com.sparta.ticketing.service.comment_like;

import com.sparta.ticketing.dto.comment_like.CommentLikeRequest;
import com.sparta.ticketing.dto.comment_like.CommentLikeResponse;
import com.sparta.ticketing.entity.CommentLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeConnectorInterface commentLikeConnectorInterface;

    public CommentLikeResponse addCommentLike(CommentLikeRequest commentLikeRequest, Long id) {
        CommentLike commentLike = commentLikeConnectorInterface.addCommentLike(commentLikeRequest, id);
        return CommentLikeResponse.from(commentLike);
    }

    public List<CommentLikeResponse> getAllByUserId(Long id) {
        List<CommentLike> commentLikes = commentLikeConnectorInterface.findAllByUserId(id);
        return commentLikes.stream().map(CommentLikeResponse::from).toList();
    }

    public List<CommentLikeResponse> getAllByCommentId(Long commentId) {
        List<CommentLike> commentLikes = commentLikeConnectorInterface.findAllByCommentId(commentId);
        return commentLikes.stream().map(CommentLikeResponse::from).toList();
    }

    public void deleteCommentLike(Long commentLikeId, Long id) {
        commentLikeConnectorInterface.deleteCommentLike(commentLikeId, id);
    }
}
