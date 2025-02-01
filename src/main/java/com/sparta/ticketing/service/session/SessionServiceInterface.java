package com.sparta.ticketing.service.session;

import com.sparta.ticketing.dto.session.AddSessionRequest;
import com.sparta.ticketing.dto.session.SessionResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SessionServiceInterface {
    void addSession(AddSessionRequest addSessionRequest);

    List<SessionResponse> getAllSessions();
}
