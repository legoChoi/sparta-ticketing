package com.sparta.ticketing.dto.concert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AddConcertRequest {
    private final String name;

    @JsonCreator
    public static AddConcertRequest from(@JsonProperty("name") String name){
        return new AddConcertRequest(name);
    }
}
