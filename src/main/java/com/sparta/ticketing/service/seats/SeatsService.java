package com.sparta.ticketing.service.seats;

import com.sparta.ticketing.dto.SeatsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatsService {
    private final SeatsConnectorInterface seatsConnectorInterface;

    public SeatsResponse addSeats(int seatNumber) {
        seatsConnectorInterface.bulkInsertSeats(seatNumber);
        return new SeatsResponse(seatNumber);
    }
}
