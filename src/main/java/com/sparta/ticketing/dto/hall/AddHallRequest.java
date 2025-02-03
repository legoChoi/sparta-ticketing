package com.sparta.ticketing.dto.hall;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddHallRequest {

    private final String name;
    private final String location;

    public AddHallRequest(String name, String location) {
        this.name = name;
        this.location = location;
    }

    @JsonCreator
    public static AddHallRequest from(
            @JsonProperty("name") String name,
            @JsonProperty("location") String location) {
        return new AddHallRequest(name, location);
    }
}
