package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.seats.SeatsConnectorInterface;
import com.sparta.ticketing.service.session.SessionConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService{
    private final ReservationConnectorInterface reservationConnector;
    private final SessionConnectorInterface sessionConnector;
    private final SeatsConnectorInterface seatsConnector;

    public void addReservation(Long sessionId, Long seatId, String name) {
        ReservationStatus status = ReservationStatus.REQUEST;
        Reservation reservation = Reservation.from(status, name);

        try {
            // session, seats repository에서 각각을 조회하기
            Session session = sessionConnector.findById(sessionId);
            Seats seat = seatsConnector.findById(seatId);

            checkAlreadyReserved(sessionId, seatId);

            reservation.setSession(session);
            reservation.setSeats(seat);
            status = purchase();

        } catch (Exception e) {
            status = ReservationStatus.FAIL;
        }

        reservation.setStatus(status);
        reservationConnector.addReservation(reservation);
    }

    public ReservationGetResponse getReservations() {
        return new ReservationGetResponse(reservationConnector.findActiveReservations());
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationConnector.findById(reservationId);
        reservationConnector.updateStatusById(reservationId, ReservationStatus.CANCEL);
    }

    private ReservationStatus purchase() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return ReservationStatus.SUCCESS;
        }
        throw new IllegalStateException("결제 실패");
    }

    private void checkAlreadyReserved(Long sessionId, Long seatId) {
        if(reservationConnector.alreadyReserved(sessionId, seatId)) {
            throw new IllegalArgumentException("Already booked seats");
        }
    }
}
