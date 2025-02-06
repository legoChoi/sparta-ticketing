package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private boolean isLike;

    public CommentLike(User user, Comment comment, boolean isLike) {
        this.user = user;
        this.comment = comment;
        this.isLike = isLike;
    }

    public static CommentLike from() {
        return new CommentLike();
    }
}
