package com.sparta.ticketing.repository.board_like;

import com.sparta.ticketing.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {
     List<BoardLike> findAllByBoardId(Long boardId);

    List<BoardLike> findAllByUserId(Long id);
}

