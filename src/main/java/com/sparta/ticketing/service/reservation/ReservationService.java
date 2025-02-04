package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.lock.RedisLockService;
import com.sparta.ticketing.service.seats.SeatsConnectorInterface;
import com.sparta.ticketing.service.session.SessionConnectorInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService{
    private final ReservationConnectorInterface reservationConnector;
    private final SessionConnectorInterface sessionConnector;
    private final SeatsConnectorInterface seatsConnector;
    private final RedisLockService redisLockService;

    @Transactional
    public void addReservation(Long sessionId, Long seatId, String name) {
        int retryCount = 0;
        int maxRetry = 10;
        long retryDelay = 1L;

        String lockVal = UUID.randomUUID().toString();
        boolean lockAcquired = redisLockService.acquireLock(lockVal);

        while (!lockAcquired && retryCount < maxRetry) {
            ++retryCount;
            try {
                Thread.sleep(retryDelay);
            } catch (InterruptedException e) {
                log.info("Thread Interrupted");
            }
            lockAcquired = redisLockService.acquireLock(lockVal);
        }
        if (!lockAcquired) {
            throw new RuntimeException("락 획득에 실패했습니다.");
        }

        ReservationStatus status = ReservationStatus.REQUEST;
        Reservation reservation = Reservation.from(status, name);

        try {
            checkAlreadyReserved(sessionId, seatId);
            Session session = sessionConnector.findById(sessionId);
            Seats seat = seatsConnector.findById(seatId);

            reservation.setSession(session);
            reservation.setSeats(seat);

            //status = ReservationStatus.SUCCESS;
            status = purchase();

    //      try {
    //      } catch (Exception e) {
    //          log.error(e.getMessage());
    //          status = ReservationStatus.FAIL;
    //      }

            reservation.setStatus(status);

            reservationConnector.addReservation(reservation);
            swapSeatAvailability(reservation.getSession(), reservation.getSeats());
        } finally {
            // 회차 좌석 수 +, 좌석 예약 여부 true
            redisLockService.releaseLock(lockVal);

        }


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

    private void swapSeatAvailability(Session session, Seats seats) {
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
