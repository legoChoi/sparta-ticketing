package com.sparta.ticketing.controller.hall;

import com.sparta.ticketing.dto.seats.SessionSeatsResponse;
import com.sparta.ticketing.service.sessionseats.SessionSeatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sessions-seats")
@RequiredArgsConstructor
public class SessionSeatsController {
    private final SessionSeatsService seatsService;

    @GetMapping
    public ResponseEntity<List<SessionSeatsResponse>> canGetSessionSeats() {
        List<SessionSeatsResponse> sessionSeatsResponses = seatsService.getSessionSeats();
        return ResponseEntity.status(HttpStatus.OK).body(sessionSeatsResponses);
    }
}
