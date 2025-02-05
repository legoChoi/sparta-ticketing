package com.sparta.ticketing.dto.anonymous;

import com.sparta.ticketing.entity.AnonymousUser;
import lombok.Getter;

@Getter
public class AnonymousUserResponse {

    private final String nickname;

    public AnonymousUserResponse(String nickname) {
        this.nickname = nickname;
    }

    public static AnonymousUserResponse from(AnonymousUser anonymousUser) {
        return new AnonymousUserResponse(anonymousUser.getNickname());
    }
}
