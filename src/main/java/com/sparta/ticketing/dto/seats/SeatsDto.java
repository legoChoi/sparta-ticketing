package com.sparta.ticketing.dto.seats;

import lombok.Getter;

@Getter
public class SeatsDto {
    private long hallId;

    public SeatsDto(long hallId) {
        this.hallId = hallId;
    }
}
