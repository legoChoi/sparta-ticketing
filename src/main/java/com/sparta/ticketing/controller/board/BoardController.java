package com.sparta.ticketing.controller.board;

import com.sparta.ticketing.dto.board.BoardListResponse;
import com.sparta.ticketing.dto.board.BoardRequest;
import com.sparta.ticketing.dto.board.BoardResponse;
import com.sparta.ticketing.dto.board.BoardUpdateRequest;
import com.sparta.ticketing.service.board.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse> addBoard(
            @Valid @RequestBody BoardRequest boardRequest,
            HttpServletRequest httpServletRequest
            ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        BoardResponse response = boardService.addBoard(boardRequest, id);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long boardId) {
        BoardResponse boardResponse = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(boardResponse);
    }

    @GetMapping
    public ResponseEntity<BoardListResponse> getAllBoard() {
        List<BoardResponse> boardResponses = boardService.getAllBoard();
        BoardListResponse boardList = BoardListResponse.from(boardResponses);
        return ResponseEntity.status(HttpStatus.OK).body(boardList);
    }

    @PatchMapping
    public ResponseEntity<BoardResponse> updateBoard(
            @Valid @RequestBody BoardUpdateRequest boardRequest,
            HttpServletRequest httpServletRequest
    ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");

        BoardResponse response = boardService.updateBoard(boardRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long boardId,
            HttpServletRequest httpServletRequest
            ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        boardService.deleteBoard(boardId, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
