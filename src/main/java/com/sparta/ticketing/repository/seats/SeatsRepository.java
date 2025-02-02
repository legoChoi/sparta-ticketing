package com.sparta.ticketing.repository.seats;

import com.sparta.ticketing.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatsRepository extends JpaRepository<Seats, Long> {
    @Query("SELECT s from Seats s join fetch s.hall h where h.id = :hallId")
    List<Seats> findAllByHallId(long hallId);
}
