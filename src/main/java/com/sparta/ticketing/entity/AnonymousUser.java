package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class AnonymousUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String password;

    @OneToMany(mappedBy = "anonymousUser", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AnonymousReservation> anonymousReservations;

    public AnonymousUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public static AnonymousUser from(String nickname, String encodePassword) {
        return new AnonymousUser(nickname,encodePassword);
    }
}
