package com.sparta.ticketing.dto.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AddSessionRequest {
    private final Long concertId;
    private final Long hallId;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
}
