package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.entity.Seats;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.repository.reservation.ReservationRepository;
import com.sparta.ticketing.repository.seats.SeatsRepository;
import com.sparta.ticketing.repository.session.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService implements ReservationServiceInterface {
    private final ReservationRepository reservationRepository;
    private final SessionRepository sessionRepository;
    private final SeatsRepository seatsRepository;

    @Override
    public void addReservation(Long sessionId, Long seatId, String name) {
        ReservationStatus status = ReservationStatus.REQUEST;
        Reservation reservation = new Reservation(status, name);

        try {
            // session, seats repository에서 각각을 조회하기
            Session session = sessionRepository.findFirstById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("no session found"));
            Seats seat = seatsRepository.findFirstById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("no seat found"));

            checkAlreadyReserved(sessionId, seatId);

            reservation.setSession(session);
            reservation.setSeats(seat);
            status = purchase();

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
        Reservation reservation = reservationRepository.findFirstByReservationId(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("No reservation found"));
        reservationRepository.updateStatusByReservationId(ReservationStatus.CANCEL, reservationId);
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
        if(reservationRepository.checkAlreadyReserved(sessionId, seatId).isPresent()) {
            throw new IllegalArgumentException("Already booked seats");
        }
    }
}
