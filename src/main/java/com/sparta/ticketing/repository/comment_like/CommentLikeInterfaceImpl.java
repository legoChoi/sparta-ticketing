package com.sparta.ticketing.repository.comment_like;

import com.sparta.ticketing.dto.comment_like.CommentLikeRequest;
import com.sparta.ticketing.entity.Comment;
import com.sparta.ticketing.entity.CommentLike;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.exception.InsufficientPermissionException;
import com.sparta.ticketing.service.comment.CommentConnectorInterface;
import com.sparta.ticketing.service.comment_like.CommentLikeConnectorInterface;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentLikeInterfaceImpl implements CommentLikeConnectorInterface {

    private final CommentLikeRepository commentLikeRepository;
    private final UserConnectInterface userConnectInterface;
    private final CommentConnectorInterface commentConnectorInterface;

    @Override
    @Transactional
    public CommentLike addCommentLike(CommentLikeRequest commentLikeRequest, Long id) {
        User user = userConnectInterface.findById(id);
        Comment comment = commentConnectorInterface.findById(commentLikeRequest.getCommentId());
        CommentLike commentLike = CommentLike.from(user, comment);
        return commentLikeRepository.save(commentLike);
    }

    @Override
    public List<CommentLike> findAllByUserId(Long id) {
        return commentLikeRepository.findAllByUserId(id);
    }

    @Override
    public List<CommentLike> findAllByCommentId(Long commentId) {
        return commentLikeRepository.findAllByCommentId(commentId);
    }

    @Override
    @Transactional
    public void deleteCommentLike(Long commentLikeId, Long id) {
        CommentLike commentLike = findById(commentLikeId);
        if (!commentLike.getUser().getId().equals(id)) {
            throw new InsufficientPermissionException(ExceptionStatus.UNAUTHORIZED_ACCESS.getMessage());
        }
        commentLikeRepository.delete(commentLike);
    }

    public CommentLike findById(Long commentLikeId) {
        return commentLikeRepository.findById(commentLikeId).
                orElseThrow(()->new IllegalArgumentException(ExceptionStatus.NOTFOUND_LIKE.getMessage()));
    }
}
