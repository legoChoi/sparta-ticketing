package com.sparta.ticketing.controller.reservation;

import com.sparta.ticketing.dto.reservation.ReservationGetResponse;
import com.sparta.ticketing.dto.reservation.ReservationPostRequest;
import com.sparta.ticketing.service.reservation.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService service;

    @PostMapping
    public ResponseEntity<String> postReservation(@Valid @RequestBody ReservationPostRequest dto) {
        return ResponseEntity.created(
            URI.create(service.addReservation(dto.getSessionId(), dto.getSeatId(), dto.getName()))).build();
    }

    @GetMapping
    public ResponseEntity<ReservationGetResponse> getReservations() {
        return ResponseEntity.ok(service.getReservations());
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("reservationId") Long reservationId) {
        service.cancelReservation(reservationId);
        return ResponseEntity.ok("삭제 완료");
    }
}
