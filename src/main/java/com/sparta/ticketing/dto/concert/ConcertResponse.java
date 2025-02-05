package com.sparta.ticketing.dto.concert;

import lombok.Getter;

import java.util.List;

@Getter
public class ConcertResponse {
    private List<String> names;

    public ConcertResponse(List<String> names) {
        this.names = names;
    }
}
