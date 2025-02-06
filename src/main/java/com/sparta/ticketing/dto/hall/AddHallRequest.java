package com.sparta.ticketing.dto.hall;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddHallRequest {

    @NotNull
    @Size(min = 2,max = 30)
    private final String name;

    @NotNull
    @Size(min = 1,max = 50)
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
