package com.sparta.ticketing.service.board_like;

import com.sparta.ticketing.dto.boardLike.BoardLikeRequest;
import com.sparta.ticketing.dto.boardLike.BoardLikeResponse;
import com.sparta.ticketing.entity.BoardLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardLikeService {
    private final BoardLikeConnectorInterface boardLikeConnectorInterface;

    public BoardLikeResponse addBoardLike(BoardLikeRequest boardLikeRequest, Long id) {
        BoardLike board = boardLikeConnectorInterface.addBoardLike(boardLikeRequest, id);
        return BoardLikeResponse.from(board);
    }

    public List<BoardLikeResponse> findAllByBoardId(Long boardId) {
        List<BoardLike> boardLikes = boardLikeConnectorInterface.findAllByBoardId(boardId);
        return boardLikes.stream().map(BoardLikeResponse::from).toList();
    }

    public List<BoardLikeResponse> findAllByUserId(Long id) {
        List<BoardLike> boardLikes = boardLikeConnectorInterface.findAllByUserId(id);
        return boardLikes.stream().map(BoardLikeResponse::from).toList();
    }

    public void deleteBoardLike(Long boardId, Long userId) {
        boardLikeConnectorInterface.deleteBoardLike(boardId,userId);
    }
}
