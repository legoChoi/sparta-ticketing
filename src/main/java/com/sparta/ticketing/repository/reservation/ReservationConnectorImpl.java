package com.sparta.ticketing.repository.reservation;

import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.service.reservation.ReservationConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationConnectorImpl implements ReservationConnectorInterface {
    private final ReservationRepository reservationRepository;

    @Override
    @Transactional
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findActiveReservations() {
        return reservationRepository.findAllWithoutFailAndCancel();
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException(ExceptionStatus.NOTFOUND_RESERVATION.getMessage()));
    }
}
