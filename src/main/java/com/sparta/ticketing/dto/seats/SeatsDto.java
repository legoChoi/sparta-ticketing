package com.sparta.ticketing.dto.seats;

import lombok.Getter;

@Getter
public class SeatsDto {
    private long hallId;
    private int seatNumber;

    public SeatsDto(long hallId, int i) {
        this.hallId = hallId;
        this.seatNumber = i;
    }
}
