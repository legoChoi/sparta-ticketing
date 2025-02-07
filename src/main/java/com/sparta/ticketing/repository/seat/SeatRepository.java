package com.sparta.ticketing.repository.seat;

import com.sparta.ticketing.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query("SELECT s from Seat s join fetch s.session ses join fetch ses.concert c where c.id = :sessionId")
    public List<Seat> findAllBySessionId(@Param("sessionId") Long sessionId);

    //@Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select s from Seat s join fetch s.session h where s.id = :seatId and s.isAvailable = true")
    public Optional<Seat> findFirstById(@Param("seatId") Long seatId);
}
