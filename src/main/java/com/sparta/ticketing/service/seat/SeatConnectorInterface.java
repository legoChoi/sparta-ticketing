package com.sparta.ticketing.service.seat;

import com.sparta.ticketing.dto.seat.SeatDto;
import com.sparta.ticketing.entity.Seat;

import java.util.List;

public interface SeatConnectorInterface {
    void bulkInsertSeats(List<SeatDto> seatNumber);

    List<Seat> saveAll(List<Seat> seats);

    List<Seat> findAll(long hallId);

    Seat findById(Long seatId);

    Seat update(Seat seats);
}
