package com.sparta.ticketing.repository.seat;

import com.sparta.ticketing.dto.seat.SeatDto;
import com.sparta.ticketing.entity.Seat;
import com.sparta.ticketing.service.seat.SeatConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatConnectorInterfaceImpl implements SeatConnectorInterface {
    private final JdbcTemplate jdbcTemplate;
    private final SeatRepository seatsRepository;

    @Override
    public void bulkInsertSeats(List<SeatDto> seatsDto) {
        String sql = "INSERT INTO seats (hall_id, seat_number) VALUES (?,?)";
        jdbcTemplate.batchUpdate(sql, seatsDto, 1000, (ps, dto) -> {
            ps.setLong(1, dto.getHallId());
            ps.setInt(2, dto.getSeatNumber());
        });
    }

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

    @Override
    public Seat update(Seat seats) {
        return seatsRepository.save(seats);
    }
}
