package com.sparta.ticketing.repository.reservation;

import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.service.reservation.ReservationConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class ReservationConnectorImpl implements ReservationConnectorInterface {
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void updateStatusById(Long reservationId, ReservationStatus status) {
        reservationRepository.updateStatusByReservationId(reservationId, status);
    }

    @Override
    public List<Reservation> findActiveReservations() {
        return reservationRepository.findAllWithoutFailAndCancel();
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationRepository.findFirstByReservationId(reservationId)
            .orElseThrow(() -> new IllegalArgumentException("No reservation found"));
    }

    @Override
    public boolean alreadyReserved(Long sessionId, Long seatId) {
        return reservationRepository.checkAlreadyReserved(sessionId, seatId).isPresent();
    }
}
