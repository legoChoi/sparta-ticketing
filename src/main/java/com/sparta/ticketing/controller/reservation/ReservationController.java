package com.sparta.ticketing.controller.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.dto.reservation.ReservationPostRequest;
import com.sparta.ticketing.service.reservation.ReservationServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationServiceInterface service;

    @PostMapping
    public ResponseEntity<String> postReservation(@RequestBody ReservationPostRequest dto) {
        service.addReservation(dto.getSessionId(), dto.getSeatId(), dto.getName());
        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<ReservationGetResponse> getReservations() {
        return ResponseEntity.ok(service.getReservations());
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long reservationId) {
        service.cancelReservation(reservationId);
        return ResponseEntity.ok("삭제 완료");
    }
}
