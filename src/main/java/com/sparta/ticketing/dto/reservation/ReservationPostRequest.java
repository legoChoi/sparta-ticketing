package com.sparta.ticketing.dto.reservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReservationPostRequest {
    private final Long sessionId;
    private final Long sessionSeatId;
    private final String name;

    private ReservationPostRequest(Long sessionId, Long sessionSeatId, String name) {
        this.sessionId = sessionId;
        this.sessionSeatId = sessionSeatId;
        this.name = name;
    }

    @JsonCreator
    public static ReservationPostRequest from(
        @JsonProperty("sessionId") Long sessionId,
        @JsonProperty("sessionSeatId") Long sessionSeatId,
        @JsonProperty("name") String name
    ) {
        return new ReservationPostRequest(sessionId, sessionSeatId, name);
    }
}
