package com.sparta.ticketing.controller.session;

import com.sparta.ticketing.dto.session.AddSessionRequest;
import com.sparta.ticketing.dto.session.SessionResponse;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.seats.SeatsService;
import com.sparta.ticketing.service.session.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final SeatsService seatsService;

    @PostMapping
    public ResponseEntity<String> addSession(@RequestBody AddSessionRequest addSessionRequest){
        Session session = sessionService.addSession(addSessionRequest);

        seatsService.addSeats(session.getId(), session.getValidSeatCount());

        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getAllSessions(){
        return ResponseEntity.ok(sessionService.getAllSessions());
    }
}
