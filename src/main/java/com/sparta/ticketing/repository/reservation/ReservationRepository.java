package com.sparta.ticketing.repository.reservation;

import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // 생성 중 실패했거나 예약 취소와 같이 soft delete 된 케이스들을 제외하고 조회
    @Query("select r from Reservation r where r.status <> 'FAIL' and r.status <> 'CANCEL'")
    public List<Reservation> findAllWithoutFailAndCancel();
    public Optional<Reservation> findFirstByReservationId(Long reservationId);

    @Query(
        "select r " +
            "from Reservation r " +
            "where r.status = 'SUCCESS' and r.session.id = :sessionId and r.seats.id = :seatId")
    Optional<Reservation> checkAlreadyReserved(@Param("sessionId") Long sessionsId, @Param("seatId") Long seatId);

    @Modifying
    @Query("update Reservation r set r.status = :status where r.reservationId = :reservationId")
    void updateStatusByReservationId(Long reservationId, ReservationStatus status);
}
