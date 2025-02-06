package com.sparta.ticketing.dto.concert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddConcertRequest {
    @NotNull
    @Size(min = 2,max = 100)
    private final String name;

    @JsonCreator
    public static AddConcertRequest from(@JsonProperty("name") String name){
        return new AddConcertRequest(name);
    }
}
