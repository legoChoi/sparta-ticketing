package com.sparta.ticketing.controller.board_like;

import com.sparta.ticketing.dto.boardLike.BoardLikeListResponse;
import com.sparta.ticketing.dto.boardLike.BoardLikeRequest;
import com.sparta.ticketing.dto.boardLike.BoardLikeResponse;
import com.sparta.ticketing.service.board_like.BoardLikeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boardLike")
@RequiredArgsConstructor
public class BoardLikeController {

    private final BoardLikeService boardLikeService;

    @PostMapping
    public ResponseEntity<BoardLikeResponse> addBoardLike(
            @Valid @RequestBody BoardLikeRequest boardLikeRequest,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        BoardLikeResponse boardLikeResponse = boardLikeService.addBoardLike(boardLikeRequest, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardLikeResponse);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardLikeListResponse> getBoardLikeByBoard(@PathVariable Long boardId) {
        List<BoardLikeResponse> boardLikeResponses = boardLikeService.findAllByBoardId(boardId);
        BoardLikeListResponse boardLikeListResponse = BoardLikeListResponse.from(boardLikeResponses);
        return ResponseEntity.status(HttpStatus.OK).body(boardLikeListResponse);
    }

    @GetMapping
    public ResponseEntity<BoardLikeListResponse> getMyBoardLike(HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        List<BoardLikeResponse> boardLikeResponses =  boardLikeService.findAllByUserId(id);
        BoardLikeListResponse boardLikeListResponse = BoardLikeListResponse.from(boardLikeResponses);
        return ResponseEntity.status(HttpStatus.OK).body(boardLikeListResponse);
    }

    @DeleteMapping("/{boardLikeId}")
    public ResponseEntity<Void> deleteBoardLike(
            @PathVariable Long boardLikeId
            ,HttpServletRequest httpServletRequest) {
        Long id = (Long) httpServletRequest.getAttribute("userID");
        boardLikeService.deleteBoardLike(boardLikeId,id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
