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
    public void addReservation(Long sessionId, Long sessionSeatId, String name) {
        // session, sessionSeat repository에서 각각을 조회하기

        Reservation reservation = new Reservation(ReservationStatus.REQUEST, name);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            reservation.setStatus(ReservationStatus.SUCCESS);
        } catch (Exception e) {
            reservation.setStatus(ReservationStatus.FAIL);
        } finally {
            reservationRepository.save(reservation);
        }
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
}
