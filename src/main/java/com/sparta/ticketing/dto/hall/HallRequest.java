package com.sparta.ticketing.dto.hall;

import lombok.Getter;

@Getter
public class HallRequest {

    private final String name;
    private final String location;

    public HallRequest(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public static HallRequest from(AddHallRequest addHallRequest) {
        return new HallRequest(addHallRequest.getName(), addHallRequest.getLocation());
    }
}
