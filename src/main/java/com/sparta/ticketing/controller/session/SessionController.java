package com.sparta.ticketing.controller.session;

import com.sparta.ticketing.dto.session.AddSessionRequest;
import com.sparta.ticketing.dto.session.SessionResponse;
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

    @PostMapping
    public ResponseEntity<String> addSession(@RequestBody AddSessionRequest addSessionRequest){
        sessionService.addSession(addSessionRequest);

        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getAllSessions(){
        return ResponseEntity.ok(sessionService.getAllSessions());
    }
}
