package com.sparta.ticketing.dto.seat;

import lombok.Getter;

@Getter
public class SeatDto {
    private long hallId;
    private int seatNumber;

    public SeatDto(long hallId, int i) {
        this.hallId = hallId;
        this.seatNumber = i;
    }
}
