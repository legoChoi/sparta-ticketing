package com.sparta.ticketing.service.comment;

import com.sparta.ticketing.dto.board.CommentRequest;
import com.sparta.ticketing.dto.comment.CommentResponse;
import com.sparta.ticketing.dto.comment.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    public CommentResponse addComment(CommentRequest commentRequest, Long id) {
        return null;
    }

    public List<CommentResponse> getMyComment(Long id) {
        return null;
    }

    public CommentResponse updateComment(CommentUpdateRequest commentUpdateRequest, Long id) {
        return null;
    }

    public List<CommentResponse> findAllByBoard(Long boardId) {
        return null;
    }

    public void deleteComment(Long commentId, Long id) {

    }
}
