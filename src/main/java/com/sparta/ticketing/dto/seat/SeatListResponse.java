package com.sparta.ticketing.dto.seat;

import lombok.Getter;

import java.util.List;

@Getter
public class SeatListResponse {
    private final List<AllSeatResponse> allSeatResponses;

    public SeatListResponse(List<AllSeatResponse> allSeatResponses) {
        this.allSeatResponses = allSeatResponses;
    }

    public static SeatListResponse from(List<AllSeatResponse> allSeatResponses) {
        return new SeatListResponse(allSeatResponses);
    }
}
