package com.sparta.ticketing.dto.auth;

import lombok.Getter;

@Getter
public class JwtResponse {
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public static JwtResponse from(String token) {
        return new JwtResponse(token);
    }
}
