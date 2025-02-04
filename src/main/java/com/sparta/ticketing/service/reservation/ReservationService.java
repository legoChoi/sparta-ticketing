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
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
        String lockKey = String.format("lock:session:%d:seat:%d", sessionId, seatId);
        String lockVal = UUID.randomUUID().toString();
        acquireLock(lockKey, lockVal);

        // 트랜잭션 종료 후 락 해제를 위한 동기화 등록
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                redisLockService.releaseLock(lockKey, lockVal);
            }
            // 필요에 따라 다른 콜백 메서드들도 구현 가능
        });

        ReservationStatus status = ReservationStatus.REQUEST;
        Reservation reservation = Reservation.from(status, name);
        checkAlreadyReserved(sessionId, seatId);
        Session session = sessionConnector.findById(sessionId);
        Seats seat = seatsConnector.findById(seatId);

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

    private void acquireLock(String lockKey, String lockVal) {
        // Deadlock 상태에 빠지지 않도록 최대 대기 시간을 설정
        int retryCount = 0;
        int maxRetry = 3;
        long retryDelay = 100L;

        boolean lockAcquired = redisLockService.acquireLock(lockKey, lockVal);

        // 최대 대기 시간이 설정된 Spin Lock
        while (!lockAcquired && retryCount < maxRetry) {
            ++retryCount;
            log.debug("repeat count: {}", retryCount);

            try {
                Thread.sleep(retryDelay);
            } catch (InterruptedException e) {
                log.info("Thread Interrupted");
            }
            lockAcquired = redisLockService.acquireLock(lockKey, lockVal);
        }
        if (!lockAcquired) {
            throw new RuntimeException("락 획득에 실패했습니다.");
        }
    }
}
