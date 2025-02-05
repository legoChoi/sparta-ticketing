package com.sparta.ticketing.dto.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class GetBestConcertResponse {
    private final String name;
}
