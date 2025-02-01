package com.sparta.ticketing.dto.concert;

import lombok.Getter;

@Getter
public class ConcertResponse {
    private String name;

    public ConcertResponse(String name) {
        this.name = name;
    }
}
