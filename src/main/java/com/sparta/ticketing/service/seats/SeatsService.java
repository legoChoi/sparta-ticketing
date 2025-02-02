package com.sparta.ticketing.service.seats;

import com.sparta.ticketing.dto.seats.AllSeatsResponse;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.service.hall.HallConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatsService {
    private final SeatsConnectorInterface seatsConnectorInterface;
    private final HallConnectorInterface hallConnectorInterface;
    public List<AllSeatsResponse> addSeats(long hallId, int seatNumber) {
        List<Seats> seats = new ArrayList<>();
        Hall byId = hallConnectorInterface.findById(hallId);
        for (int i = 1; i <= seatNumber; i++) {
            seats.add(Seats.from(byId,i));
        }
        List<Seats> seatsList = seatsConnectorInterface.saveAll(seats);
        return seatsList.stream().map(AllSeatsResponse::from).toList();
    }

    public List<AllSeatsResponse> getAll(long hallId) {
        List<Seats> all = seatsConnectorInterface.findAll(hallId);
        return all.stream().map(AllSeatsResponse::from).toList();
    }
}
