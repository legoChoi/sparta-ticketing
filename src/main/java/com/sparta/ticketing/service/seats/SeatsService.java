package com.sparta.ticketing.service.seats;

import com.sparta.ticketing.dto.seats.SeatsDto;
import com.sparta.ticketing.dto.seats.SeatsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatsService {
    private final SeatsConnectorInterface seatsConnectorInterface;

    public SeatsResponse addSeats(long hallId, int seatNumber) {
        List<SeatsDto> seats = new ArrayList<>();
        for (int i = 1; i <= seatNumber; i++) {
            seats.add(new SeatsDto(hallId));
        }

        seatsConnectorInterface.bulkInsertSeats(seats);
        return new SeatsResponse(seatNumber);
    }
}
