package com.sparta.ticketing.dto.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetConcertResponse {
    private final Long id;
    private final String name;
}
