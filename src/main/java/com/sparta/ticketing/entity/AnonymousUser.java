package com.sparta.ticketing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AnonymousUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String password;

    public AnonymousUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public static AnonymousUser from(String nickname, String encodePassword) {
        return new AnonymousUser(nickname,encodePassword);
    }
}
