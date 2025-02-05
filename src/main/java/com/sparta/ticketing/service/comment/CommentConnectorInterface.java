package com.sparta.ticketing.service.comment;

import com.sparta.ticketing.dto.comment.CommentRequest;
import com.sparta.ticketing.dto.comment.CommentUpdateRequest;
import com.sparta.ticketing.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentConnectorInterface {
    Comment addComment(CommentRequest commentRequest, Long id);

    List<Comment> getMyComment(Long id);

    List<Comment> findAllByBoardId(Long boardId);

    Comment updateComment(CommentUpdateRequest commentUpdateRequest, Long id);

    void deleteComment(Long commentId, Long id);
}
