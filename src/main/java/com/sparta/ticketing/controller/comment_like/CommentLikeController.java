package com.sparta.ticketing.controller.comment_like;

import com.sparta.ticketing.dto.comment_like.CommentLikeListResponse;
import com.sparta.ticketing.dto.comment_like.CommentLikeRequest;
import com.sparta.ticketing.dto.comment_like.CommentLikeResponse;
import com.sparta.ticketing.service.comment_like.CommentLikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<CommentLikeResponse> addCommentLike(
            @RequestBody CommentLikeRequest commentLikeRequest,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        CommentLikeResponse commentLikeResponse = commentLikeService.addCommentLike(commentLikeRequest, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentLikeResponse);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentLikeListResponse> getAllByCommentId(@PathVariable Long commentId) {
        List<CommentLikeResponse> commentLikeResponses = commentLikeService.getAllByCommentId(commentId);
        CommentLikeListResponse commentLikeListResponse = CommentLikeListResponse.from(commentLikeResponses);
        return ResponseEntity.status(HttpStatus.OK).body(commentLikeListResponse);
    }

    @GetMapping
    public ResponseEntity<CommentLikeListResponse> getAllByUserId(HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        List<CommentLikeResponse> commentLikeResponses = commentLikeService.getAllByUserId(id);
        CommentLikeListResponse commentLikeListResponse = CommentLikeListResponse.from(commentLikeResponses);
        return ResponseEntity.status(HttpStatus.OK).body(commentLikeListResponse);
    }

    @DeleteMapping("/{commentLikeId}")
    public ResponseEntity<Void> deleteCommentLike(
            @PathVariable Long commentLikeId,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        commentLikeService.deleteCommentLike(commentLikeId, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
