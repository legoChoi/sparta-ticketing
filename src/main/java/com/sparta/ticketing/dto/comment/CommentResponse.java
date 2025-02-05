package com.sparta.ticketing.dto.comment;

import com.sparta.ticketing.entity.Comment;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class CommentResponse {
    private final Long commentId;

    private final Long boardId;

    private final String comments;

    public CommentResponse(Long commentId, Long boardId, String comments) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.comments = comments;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getContents());
    }
}
