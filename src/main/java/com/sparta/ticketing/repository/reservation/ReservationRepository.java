package com.sparta.ticketing.repository.reservation;

import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.status <> 'FAIL' and r.status <> 'CANCEL'")
    public List<Reservation> findAllWithoutFailAndCancel();
    public Optional<Reservation> findFirstById(Long reservationId);

    @Modifying
    @Query("update Reservation r set r.status = :status where r.reservationId = :reservationId")
    void updateStatusByReservationId(ReservationStatus status, Long reservationId);
}
