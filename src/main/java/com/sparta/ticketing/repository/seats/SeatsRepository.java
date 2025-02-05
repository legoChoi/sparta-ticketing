package com.sparta.ticketing.repository.seats;

import com.sparta.ticketing.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatsRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s from Seat s join fetch s.session, s.session.concert h where h.id = :sessionId")
    public List<Seat> findAllBySessionId(long sessionId);

    @Query("select s from Seat s join fetch s.session h where s.id = :seatId")
    public Optional<Seat> findFirstById(@Param("seatId") Long seatId);
}
