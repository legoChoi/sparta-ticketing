package com.sparta.ticketing.service.seats;

import com.sparta.ticketing.dto.seats.SeatsDto;
import com.sparta.ticketing.entity.Seat;

import java.util.List;

public interface SeatsConnectorInterface {
    void bulkInsertSeats(List<SeatsDto> seatNumber);

    List<Seat> saveAll(List<Seat> seats);

    List<Seat> findAll(long hallId);

    Seat findById(Long seatId);

    Seat update(Seat seats);
}
