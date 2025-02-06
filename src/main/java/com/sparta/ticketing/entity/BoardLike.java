package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isLike;

    public BoardLike(Board board, User user, boolean isLike) {
        this.board = board;
        this.user = user;
        this.isLike = isLike;
    }

    public static BoardLike from(User user, Board board,boolean isLike) {
        return new BoardLike(board,user,isLike);
    }
}
