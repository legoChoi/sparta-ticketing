package com.sparta.ticketing.dto.seats;

import lombok.Getter;

@Getter
public class SeatsResponse {
    private final int seatsNumber;

    public SeatsResponse(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }
}
