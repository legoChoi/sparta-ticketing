package com.sparta.ticketing.entity;

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

    public static Board from() {
        return new Board();
    }
}
