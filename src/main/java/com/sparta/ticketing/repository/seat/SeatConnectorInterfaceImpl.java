package com.sparta.ticketing.repository.seat;

import com.sparta.ticketing.entity.Seat;
import com.sparta.ticketing.service.seat.SeatConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatConnectorInterfaceImpl implements SeatConnectorInterface {
    private final SeatRepository seatsRepository;

    @Override
    public List<Seat> saveAll(List<Seat> seats) {
        List<Seat> seatsList = seatsRepository.saveAll(seats);
        return seatsList;
    }

    @Override
    public List<Seat> findAll(long sessionId) {
        return seatsRepository.findAllBySessionId(sessionId);
    }

    @Override
    public Seat findById(Long seatId) {
        return seatsRepository.findFirstById(seatId)
            .orElseThrow(() -> new IllegalArgumentException("no seat found"));
    }
}
