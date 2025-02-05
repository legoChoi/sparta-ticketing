package com.sparta.ticketing.service.comment;

import com.sparta.ticketing.dto.board.CommentRequest;
import com.sparta.ticketing.dto.comment.CommentResponse;
import com.sparta.ticketing.dto.comment.CommentUpdateRequest;
import com.sparta.ticketing.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentConnectorInterface commentConnectorInterface;

    public CommentResponse addComment(CommentRequest commentRequest, Long id) {
        Comment comment = commentConnectorInterface.addComment(commentRequest, id);
        return CommentResponse.from(comment);
    }

    public List<CommentResponse> getMyComment(Long id) {
        List<Comment> comments = commentConnectorInterface.getMyComment(id);
        return comments.stream().map(CommentResponse::from).toList();
    }

    public List<CommentResponse> findAllByBoard(Long boardId) {
        List<Comment> comments = commentConnectorInterface.findAllByBoardId(boardId);
        return comments.stream().map(CommentResponse::from).toList();
    }

    public CommentResponse updateComment(CommentUpdateRequest commentUpdateRequest, Long id) {
        Comment comment = commentConnectorInterface.updateComment(commentUpdateRequest, id);
        return CommentResponse.from(comment);
    }

    public void deleteComment(Long commentId, Long id) {
        commentConnectorInterface.deleteComment(commentId, id);
    }
}
