package com.sparta.ticketing.controller.seat;

import com.sparta.ticketing.dto.seat.AllSeatResponse;
import com.sparta.ticketing.dto.seat.SeatListResponse;
import com.sparta.ticketing.service.seat.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatsService;

    @GetMapping("/{sessionId}")
    public ResponseEntity<SeatListResponse> getAllSeats(@PathVariable long sessionId) {
        List<AllSeatResponse> all = seatsService.getAll(sessionId);
        SeatListResponse seatListResponse = SeatListResponse.from(all);
        return ResponseEntity.status(HttpStatus.OK).body(seatListResponse);
    }
}
