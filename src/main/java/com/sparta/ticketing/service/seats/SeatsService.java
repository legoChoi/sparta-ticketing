package com.sparta.ticketing.service.seats;

import com.sparta.ticketing.dto.seats.AllSeatsResponse;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.hall.HallConnectorInterface;
import com.sparta.ticketing.service.session.SessionConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatsService {
    private final SeatsConnectorInterface seatsConnectorInterface;
    private final SessionConnectorInterface sessionConnectorInterface;

    public void addSeats(long sessionId, int seatNumber) {
        List<Seats> seats = new ArrayList<>();
        Session byId = sessionConnectorInterface.findById(sessionId);
        for (int i = 1; i <= seatNumber; i++) {
            seats.add(Seats.from(byId,i));
        }
        seatsConnectorInterface.saveAll(seats);
    }

    public List<AllSeatsResponse> getAll(long hallId) {
        List<Seats> all = seatsConnectorInterface.findAll(hallId);
        return all.stream().map(AllSeatsResponse::from).toList();
    }
}
