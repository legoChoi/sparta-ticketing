package com.sparta.ticketing.repository.seat;

import com.sparta.ticketing.entity.Seat;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.service.seat.SeatConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatConnectorInterfaceImpl implements SeatConnectorInterface {
    private final SeatRepository seatsRepository;

    @Override
    @Transactional
    public List<Seat> saveAll(List<Seat> seats) {
        return seatsRepository.saveAll(seats);
    }

    @Override
    public List<Seat> findAll(long sessionId) {
        return seatsRepository.findAllBySessionId(sessionId);
    }

    @Override
    public Seat findById(Long seatId) {
        return seatsRepository.findFirstById(seatId)
            .orElseThrow(() -> new IllegalArgumentException(ExceptionStatus.NOTFOUND_SEAT.getMessage()));
    }
}
