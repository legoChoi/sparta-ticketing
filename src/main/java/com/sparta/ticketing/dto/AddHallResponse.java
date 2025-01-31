package com.sparta.ticketing.dto;

import com.sparta.ticketing.entity.Hall;

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
