package com.sparta.ticketing.repository.seats;

import com.sparta.ticketing.dto.seats.SeatsDto;
import com.sparta.ticketing.service.seats.SeatsConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class SeatsConnectorInterfaceImpl implements SeatsConnectorInterface {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void bulkInsertSeats(List<SeatsDto> seatsDto) {
        String sql = "INSERT INTO seats (hall_id) VALUES (?)";
        jdbcTemplate.batchUpdate(sql, seatsDto, 1000, (ps, dto) -> {
            ps.setLong(1, dto.getHallId());
        });
    }
}
