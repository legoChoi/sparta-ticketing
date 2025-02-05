package com.sparta.ticketing.repository.comment;


import com.sparta.ticketing.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUserId(Long id);

    List<Comment> findAllByBoardId(Long boardId);
}
