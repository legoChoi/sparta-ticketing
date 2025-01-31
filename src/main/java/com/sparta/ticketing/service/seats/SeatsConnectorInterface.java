package com.sparta.ticketing.service.seats;

import org.springframework.stereotype.Component;

@Component
public interface SeatsConnectorInterface {
    void bulkInsertSeats(int seatNumber);
}
