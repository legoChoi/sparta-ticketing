package com.sparta.ticketing.repository.board;

import com.sparta.ticketing.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
