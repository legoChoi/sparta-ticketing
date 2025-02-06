package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String contents;

    private int star;


    public Review(Session session, User user, String contents, int star) {
        this.session = session;
        this.user = user;
        this.contents = contents;
        this.star = star;
    }

    public static Review from(User user, Session session, String content, int star) {
        return new Review(session, user, content, star);
    }

    public void updateContents(String content) {
        this.contents = content;
    }

    public void updateStar(int star) {
        this.star = star;
    }
}
