package com.sparta.ticketing.service.seats;

import com.sparta.ticketing.dto.seats.SeatsDto;
import com.sparta.ticketing.entity.Seats;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SeatsConnectorInterface {
    void bulkInsertSeats(List<SeatsDto> seatNumber);

    List<Seats> saveAll(List<Seats> seats);

    List<Seats> findAll(long hallId);

    Seats findById(Long seatId);
}
