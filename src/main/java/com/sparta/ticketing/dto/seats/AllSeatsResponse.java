package com.sparta.ticketing.dto.seats;

import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.entity.Session;
import lombok.Getter;

@Getter
public class AllSeatsResponse {
    private long id;
    private Session session;
    private int seatNumber;

    public AllSeatsResponse(long id, Session session, int seatNumber) {
        this.id = id;
        this.session = session;
        this.seatNumber = seatNumber;
    }

    public static AllSeatsResponse from(Seats seats) {
        return new AllSeatsResponse(
                seats.getId(),
                seats.getSession(),
                seats.getSeatNumber()
        );
    }
}
