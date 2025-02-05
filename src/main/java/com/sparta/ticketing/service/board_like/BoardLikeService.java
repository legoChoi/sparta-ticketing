package com.sparta.ticketing.service.board_like;

import com.sparta.ticketing.dto.board.BoardResponse;
import com.sparta.ticketing.dto.boardLike.BoardLikeRequest;
import com.sparta.ticketing.dto.boardLike.BoardLikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardLikeService {
    public BoardLikeResponse addBoardLike(BoardLikeRequest boardLikeRequest, Long id) {
        return null;
    }

    public List<BoardLikeResponse> findAllByBoardId(Long boardId) {
        return null;
    }

    public List<BoardLikeResponse> findAllByUserId(Long id) {
        return null;
    }

    public void deleteBoardLike(Long id) {

    }
}
