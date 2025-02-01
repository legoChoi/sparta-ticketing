package com.sparta.ticketing.dto.concert;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddConcertRequest {
    private final String name;
}
