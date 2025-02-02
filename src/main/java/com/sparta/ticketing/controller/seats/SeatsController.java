package com.sparta.ticketing.controller.seats;

import com.sparta.ticketing.dto.seats.AllSeatsResponse;
import com.sparta.ticketing.service.seats.SeatsService;
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
public class SeatsController {
    private final SeatsService seatsService;

    @GetMapping("/{hallId}")
    public ResponseEntity<List<AllSeatsResponse>> getAllSeats(@PathVariable long hallId) {
        List<AllSeatsResponse> all = seatsService.getAll(hallId);
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }
}
