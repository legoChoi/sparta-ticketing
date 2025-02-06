package com.sparta.ticketing.repository.board_like;

import com.sparta.ticketing.dto.boardLike.BoardLikeRequest;
import com.sparta.ticketing.entity.BoardLike;
import com.sparta.ticketing.service.board_like.BoardLikeConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardLikeConnectorInterfaceImpl implements BoardLikeConnectorInterface {
    private final BoardLikeRepository boardLikeRepository;

    @Override
    public BoardLike addBoardLike(BoardLikeRequest boardLikeRequest, Long id) {

        return null;
    }

    @Override
    public List<BoardLike> findAllByBoardId(Long boardId) {
        return List.of();
    }

    @Override
    public List<BoardLike> findAllByUserId(Long id) {
        return List.of();
    }

    @Override
    public void deleteBoardLike(Long boardId, Long userId) {

    }
}
