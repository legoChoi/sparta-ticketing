package com.sparta.ticketing.repository.seats;

import com.sparta.ticketing.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatsRepository extends JpaRepository<Seats, Long> {
    @Query("SELECT s from Seats s join fetch s.hall h where h.id = :hallId")
    public List<Seats> findAllByHallId(long hallId);

    @Query("select s from Seats s join fetch s.hall h where s.id = :seatId")
    public Optional<Seats> findFirstById(@Param("seatId") Long seatId);
}
