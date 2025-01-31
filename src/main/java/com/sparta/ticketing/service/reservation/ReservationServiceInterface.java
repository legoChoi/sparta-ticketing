package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ReservationServiceInterface {
    public void addReservation(Long sessionId, Long sessionSeatId, String name);
    public ReservationGetResponse getReservations();
    public void cancelReservation(Long reservationId);
}
