package com.sparta.ticketing.controller.session;

import com.sparta.ticketing.dto.session.AddSessionRequest;
import com.sparta.ticketing.dto.session.SessionsResponse;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.seat.SeatService;
import com.sparta.ticketing.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final SeatService seatsService;

    @PostMapping
    public ResponseEntity<String> addSession(@RequestBody AddSessionRequest addSessionRequest){
        Session session = sessionService.addSession(addSessionRequest);

        seatsService.addSeats(session.getId(), session.getValidSeatCount());

        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<SessionsResponse> getAllSessions(){
        return ResponseEntity.ok(sessionService.getAllSessions());
    }
}
