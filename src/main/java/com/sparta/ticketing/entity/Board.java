package com.sparta.ticketing.entity;

import com.sparta.ticketing.dto.board.BoardRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String contents;


    public Board(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public static Board from(BoardRequest boardRequest, User user) {
        return new Board(
                user,
                boardRequest.getTitle(),
                boardRequest.getContents());
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
