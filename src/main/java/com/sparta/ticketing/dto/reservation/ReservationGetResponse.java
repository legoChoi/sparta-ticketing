package com.sparta.ticketing.dto.reservation;

import com.sparta.ticketing.entity.Reservation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReservationGetResponse {
    private final List<Reservation> reservationList;
}
