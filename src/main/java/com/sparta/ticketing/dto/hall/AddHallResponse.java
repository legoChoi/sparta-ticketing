package com.sparta.ticketing.dto.hall;

import com.sparta.ticketing.dto.seats.AllSeatsResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class AddHallResponse {
    private final long id;
    private final String name;
    private final String location;
    private final List<AllSeatsResponse> allSeatsResponses;

    public AddHallResponse(long id, String name, String location,List<AllSeatsResponse> allSeatsResponses) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.allSeatsResponses = allSeatsResponses;
    }

    public static AddHallResponse from(HallResponse hallResponse, List<AllSeatsResponse> allSeatsResponses) {
        return new AddHallResponse(
                hallResponse.getId(),
                hallResponse.getName(),
                hallResponse.getLocation(),
                allSeatsResponses);
    }
}
