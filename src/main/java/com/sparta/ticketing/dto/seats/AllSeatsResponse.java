package com.sparta.ticketing.dto.seats;

import com.sparta.ticketing.entity.Seat;
import lombok.Getter;

@Getter
public class AllSeatsResponse {
    private long id;
    private String sessionName;
    private int seatNumber;
    private boolean isAvailable;

    public AllSeatsResponse(long id, String session, int seatNumber,boolean isAvailable) {
        this.id = id;
        this.sessionName = session;
        this.seatNumber = seatNumber;
        this.isAvailable = isAvailable;
    }

    public static AllSeatsResponse from(Seat seats) {
        return new AllSeatsResponse(
                seats.getId(),
                seats.getSession().getConcert().getName(),
                seats.getSeatNumber(),
                seats.isAvailable()
        );
    }
}
