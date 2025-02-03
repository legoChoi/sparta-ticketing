package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.repository.reservation.ReservationConnectorInterface;
import com.sparta.ticketing.service.seats.SeatsConnectorInterface;
import com.sparta.ticketing.service.session.SessionConnectorInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService implements ReservationServiceInterface {
    private final ReservationConnectorInterface reservationConnector;
    private final SessionConnectorInterface sessionConnector;
    private final SeatsConnectorInterface seatsConnector;

    @Override
    @Transactional
    public void addReservation(Long sessionId, Long seatId, String name) {
        ReservationStatus status = ReservationStatus.REQUEST;
        Reservation reservation = Reservation.from(status, name);

        try {
            Session session = sessionConnector.findById(sessionId);
            Seats seat = seatsConnector.findById(seatId);

            checkAlreadyReserved(sessionId, seatId);

            reservation.setSession(session);
            reservation.setSeats(seat);
            status = purchase();

        } catch (Exception e) {
            log.error(e.getMessage());
            status = ReservationStatus.FAIL;
        }

        reservation.setStatus(status);
        reservationConnector.addReservation(reservation);
    }

    @Override
    public ReservationGetResponse getReservations() {
        return new ReservationGetResponse(reservationConnector.findActiveReservations());
    }

    @Override
    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationConnector.findById(reservationId);
        reservationConnector.updateStatusById(reservationId, ReservationStatus.CANCEL);
    }

    private ReservationStatus purchase() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
        return ReservationStatus.SUCCESS;
    }

    private void checkAlreadyReserved(Long sessionId, Long seatId) {
        if(reservationConnector.alreadyReserved(sessionId, seatId)) {
            throw new IllegalArgumentException("Already booked seats");
        }
    }
}
