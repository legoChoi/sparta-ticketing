package com.sparta.ticketing.dto.reservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ReservationPostRequest {
    private final Long sessionId;
    private final Long seatId;
    private final String name;

    private ReservationPostRequest(Long sessionId, Long seatId, String name) {
        this.sessionId = sessionId;
        this.seatId = seatId;
        this.name = name;
    }

    @JsonCreator
    public static ReservationPostRequest from(
        @JsonProperty("sessionId") Long sessionId,
        @JsonProperty("seatId") Long seatId,
        @JsonProperty("name") String name
    ) {
        return new ReservationPostRequest(sessionId, seatId, name);
    }
}
