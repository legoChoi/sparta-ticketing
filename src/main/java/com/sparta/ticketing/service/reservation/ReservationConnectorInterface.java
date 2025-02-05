package com.sparta.ticketing.service.reservation;

import com.sparta.ticketing.entity.Reservation;
import com.sparta.ticketing.entity.ReservationStatus;

import java.util.List;

public interface ReservationConnectorInterface {
    public Reservation addReservation(Reservation reservation);
    public List<Reservation> findActiveReservations();
    public Reservation findById(Long reservationId);
}
