package com.sparta.ticketing.entity;

import com.sparta.ticketing.dto.comment.CommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String contents;

    public Comment(User user, Board board, String contents) {
        this.user = user;
        this.board = board;
        this.contents = contents;
    }

    public static Comment from(CommentRequest commentRequest, User user, Board board) {
        return new Comment(user,board, commentRequest.getContents());
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
