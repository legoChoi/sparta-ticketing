package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.annotation.RedisLock;
import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.entity.Seat;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.seat.SeatConnectorInterface;
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
    private final SeatConnectorInterface seatsConnector;

    @Transactional
    @RedisLock(key = "'lock:session:' + #sessionId + ':seat:' + #seatId")
    public void addReservation(Long sessionId, Long seatId, String name) {
        ReservationStatus status = ReservationStatus.REQUEST;
        Session session = sessionConnector.findById(sessionId);
        Seat seat = seatsConnector.findById(seatId);
        Reservation reservation = Reservation.from(status, name, session, seat);

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
        reservation.setStatus(ReservationStatus.CANCEL);
        // 회차 좌석 수 -, 좌석 예약 여부 false
        swapSeatAvailability(reservation.getSession(), reservation.getSeats());
    }

    private void swapSeatAvailability(Session session, Seat seat) {
        seat.swapAvailability();
        session.countPlusMinus(seat.isAvailable());
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

}
