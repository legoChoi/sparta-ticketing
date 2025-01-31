package com.sparta.ticketing.service.seats;

import com.sparta.ticketing.dto.seats.SeatsDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SeatsConnectorInterface {
    void bulkInsertSeats(List<SeatsDto> seatNumber);
}
