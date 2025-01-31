package com.sparta.ticketing.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddHallRequest {

    private final String name;
    private final String location;
    private final int seatNumber;

    public AddHallRequest(String name, String location, int seatNumber) {
        this.name = name;
        this.location = location;
        this.seatNumber = seatNumber;
    }

    @JsonCreator
    public static AddHallRequest from(
            @JsonProperty("name") String name,
            @JsonProperty("location") String location,
            @JsonProperty("seatNumber") int seatNumber) {
        return new AddHallRequest(name, location, seatNumber);
    }
}
