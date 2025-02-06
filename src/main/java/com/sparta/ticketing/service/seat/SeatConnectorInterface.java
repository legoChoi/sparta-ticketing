package com.sparta.ticketing.service.seat;

import com.sparta.ticketing.entity.Seat;

import java.util.List;

public interface SeatConnectorInterface {

    List<Seat> saveAll(List<Seat> seats);

    List<Seat> findAll(long hallId);

    Seat findById(Long seatId);
}
