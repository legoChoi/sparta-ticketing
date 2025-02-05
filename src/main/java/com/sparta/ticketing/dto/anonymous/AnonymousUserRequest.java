package com.sparta.ticketing.dto.anonymous;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AnonymousUserRequest {

    private final String nickname;
    private final String password;

    public AnonymousUserRequest(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @JsonCreator
    public static AnonymousUserRequest from(
            @JsonProperty("nickname") String nickname,
            @JsonProperty("password") String password
    ) {
        return new AnonymousUserRequest(nickname, password);
    }
}
