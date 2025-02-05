package com.sparta.ticketing.dto.seat;

import com.sparta.ticketing.entity.Seat;
import lombok.Getter;

@Getter
public class AllSeatResponse {
    private long id;
    private String sessionName;
    private int seatNumber;
    private boolean isAvailable;

    public AllSeatResponse(long id, String session, int seatNumber, boolean isAvailable) {
        this.id = id;
        this.sessionName = session;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    public static AllSeatResponse from(Seat seats) {
        return new AllSeatResponse(
                seats.getId(),
                seats.getSession().getConcert().getName(),
                seats.getSeatNumber(),
                seats.isAvailable()
        );
    }
}
