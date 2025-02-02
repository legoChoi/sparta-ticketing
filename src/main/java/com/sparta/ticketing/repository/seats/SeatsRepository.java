package com.sparta.ticketing.repository.seats;

import com.sparta.ticketing.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatsRepository extends JpaRepository<Seats, Long> {
    List<Seats> findAllByHallId(long hallId);
}
