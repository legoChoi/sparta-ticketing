package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationServiceInterface {
    private final ReservationRepository reservationRepository;

    @Override
    public void addReservation(Long sessionId, Long seatId, String name) {
        ReservationStatus status = ReservationStatus.REQUEST;
        Reservation reservation = new Reservation(status, name);

        try {
            // session, seats repository에서 각각을 조회하기

            checkAlreadyReserved(sessionId, seatId);

            status = purchase(status);

        } catch (Exception e) {
            status = ReservationStatus.FAIL;
        }

        reservation.setStatus(status);
        reservationRepository.save(reservation);
    }

    @Override
    public ReservationGetResponse getReservations() {
        return new ReservationGetResponse(reservationRepository.findAllWithoutFailAndCancel());
    }

    @Override
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findFirstById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("No reservation found"));
        reservationRepository.updateStatusByReservationId(ReservationStatus.CANCEL, reservationId);
    }

    private ReservationStatus purchase(ReservationStatus status) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return ReservationStatus.SUCCESS;
        }
        throw new IllegalStateException("결제 실패");
    }

    private void checkAlreadyReserved(Long sessionId, Long seatId) {
        if(reservationRepository.checkAlreadyReserved(sessionId, seatId).isPresent()) {
            throw new IllegalArgumentException("Already booked seats");
        }
    }
}
