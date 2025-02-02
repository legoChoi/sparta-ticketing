package com.sparta.ticketing.dto.seats;

import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Seats;
import lombok.Getter;

@Getter
public class AllSeatsResponse {
    private long id;
    private Hall hall;
    private int seatNumber;

    public AllSeatsResponse(long id, Hall hall, int seatNumber) {
        this.id = id;
        this.hall = hall;
        this.seatNumber = seatNumber;
    }

    public static AllSeatsResponse from(Seats seats) {
        return new AllSeatsResponse(
                seats.getId(),
                seats.getHall(),
                seats.getSeatNumber()
        );
    }
}
