package com.sparta.ticketing.controller.comment;

import com.sparta.ticketing.dto.comment.CommentListResponse;
import com.sparta.ticketing.dto.board.CommentRequest;
import com.sparta.ticketing.dto.comment.CommentResponse;
import com.sparta.ticketing.dto.comment.CommentUpdateRequest;
import com.sparta.ticketing.service.comment.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<CommentResponse> addComment(
            @RequestBody CommentRequest commentRequest,
            HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        CommentResponse commentResponse = commentService.addComment(commentRequest, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    @GetMapping
    public ResponseEntity<CommentListResponse> getMyComment(HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        List<CommentResponse> commentResponses = commentService.getMyComment(id);
        CommentListResponse commentListResponse = CommentListResponse.from(commentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(commentListResponse);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<CommentListResponse> getCommentByBoard(@PathVariable Long boardId) {
        List<CommentResponse> commentResponses = commentService.findAllByBoard(boardId);
        CommentListResponse commentListResponse = CommentListResponse.from(commentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(commentListResponse);
    }

    @PatchMapping
    public ResponseEntity<CommentResponse> updateComment
            (@RequestBody CommentUpdateRequest commentUpdateRequest,
             HttpServletRequest httpServletRequest
            ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        CommentResponse commentResponse = commentService.updateComment(commentUpdateRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(commentResponse);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        commentService.deleteComment(commentId, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
