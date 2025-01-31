package com.sparta.ticketing.dto;

import com.sparta.ticketing.entity.Hall;
import lombok.Getter;

@Getter
public class HallResponse {
    private final long id;
    private final String name;
    private final String location;

    public HallResponse(long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public static HallResponse from(Hall hall) {
        return new HallResponse(
                hall.getId(),
                hall.getName(),
                hall.getLocation());
    }
}
