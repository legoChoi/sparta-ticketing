package com.sparta.ticketing.repository.seats;

import com.sparta.ticketing.dto.seats.SeatsDto;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.service.seats.SeatsConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SeatsConnectorInterfaceImpl implements SeatsConnectorInterface {
    private final JdbcTemplate jdbcTemplate;
    private final SeatsRepository seatsRepository;

    @Override
    public void bulkInsertSeats(List<SeatsDto> seatsDto) {
        String sql = "INSERT INTO seats (hall_id, seat_number) VALUES (?,?)";
        jdbcTemplate.batchUpdate(sql, seatsDto, 1000, (ps, dto) -> {
            ps.setLong(1, dto.getHallId());
            ps.setInt(2, dto.getSeatNumber());
        });
    }

    @Override
    public List<Seats> saveAll(List<Seats> seats) {
        List<Seats> seatsList = seatsRepository.saveAll(seats);
        return seatsList;
    }

    @Override
    public List<Seats> findAll(long hallId) {
        return seatsRepository.findAllBySessionId(hallId);
    }

    @Override
    public Seats findById(Long seatId) {
        return seatsRepository.findFirstById(seatId)
            .orElseThrow(() -> new IllegalArgumentException("no seat found"));
    }

    @Override
    public Seats update(Seats seats) {
        return seatsRepository.save(seats);
    }
}
