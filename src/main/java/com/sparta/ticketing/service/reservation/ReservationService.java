package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.annotation.RedisLock;
import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.entity.Seat;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.seats.SeatsConnectorInterface;
import com.sparta.ticketing.service.session.SessionConnectorInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService{
    private final ReservationConnectorInterface reservationConnector;
    private final SessionConnectorInterface sessionConnector;
    private final SeatsConnectorInterface seatsConnector;

    @Transactional
    @RedisLock(key = "'lock:session:' + #sessionId + ':seat:' + #seatId")
    public void addReservation(Long sessionId, Long seatId, String name) {
        ReservationStatus status = ReservationStatus.REQUEST;
        Reservation reservation = Reservation.from(status, name);
        checkAlreadyReserved(sessionId, seatId);
        Session session = sessionConnector.findById(sessionId);
        Seat seat = seatsConnector.findById(seatId);

        reservation.setSession(session);
        reservation.setSeats(seat);

        //status = ReservationStatus.SUCCESS;
        status = purchase();

        reservation.setStatus(status);

        reservationConnector.addReservation(reservation);
        swapSeatAvailability(reservation.getSession(), reservation.getSeats());

    }

    public ReservationGetResponse getReservations() {
        return new ReservationGetResponse(reservationConnector.findActiveReservations());
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationConnector.findById(reservationId);
        reservationConnector.updateStatusById(reservationId, ReservationStatus.CANCEL);
        // 회차 좌석 수 -, 좌석 예약 여부 false
        swapSeatAvailability(reservation.getSession(), reservation.getSeats());
    }

    private void swapSeatAvailability(Session session, Seat seats) {
        seats.swapAvailability();
        session.countPlusMinus(seats.isAvailable());
        sessionConnector.update(session);
        seatsConnector.update(seats);
    }

    private ReservationStatus purchase() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            //return ReservationStatus.FAIL;
        }
        return ReservationStatus.SUCCESS;
    }

    private void checkAlreadyReserved(Long sessionId, Long seatId) {
        if(reservationConnector.alreadyReserved(sessionId, seatId)) {
            throw new IllegalArgumentException("Already booked seats");
        }
    }
}
