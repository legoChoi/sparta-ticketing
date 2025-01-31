package com.sparta.ticketing.dto.hall;

import com.sparta.ticketing.dto.seats.SeatsResponse;

public class AddHallResponse {
    private final long id;
    private final String name;
    private final String location;
    private final int seatNumber;

    public AddHallResponse(long id, String name, String location, int seatNumber) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.seatNumber = seatNumber;
    }

    public static AddHallResponse from(HallResponse hallResponse, SeatsResponse seatsResponse) {
        return new AddHallResponse(
                hallResponse.getId(),
                hallResponse.getName(),
                hallResponse.getLocation(),
                seatsResponse.getSeatsNumber());
    }
}
