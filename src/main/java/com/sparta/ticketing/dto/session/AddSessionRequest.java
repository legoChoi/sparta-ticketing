package com.sparta.ticketing.dto.session;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private final Integer validSeatCount;

    @JsonCreator
    public static AddSessionRequest from(
            @JsonProperty("concertId") Long concertId,
            @JsonProperty("hallId") Long hallId,
            @JsonProperty("startDateTime") LocalDateTime startDateTime,
            @JsonProperty("endDateTime") LocalDateTime endDateTime,
            @JsonProperty("validSeatCount") Integer validSeatCount
    ){
        return new AddSessionRequest(concertId, hallId, startDateTime, endDateTime, validSeatCount);
    }
}
