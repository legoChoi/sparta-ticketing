package com.sparta.ticketing.service.session;

import com.sparta.ticketing.dto.session.AddSessionRequest;
import com.sparta.ticketing.dto.session.SessionResponse;
import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.concert.ConcertConnectorInterface;
import com.sparta.ticketing.service.hall.HallConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService{

    private final SessionConnectorInterface sessionConnectorInterface;
    private final HallConnectorInterface hallConnectorInterface;
    private final ConcertConnectorInterface concertConnectorInterface;

    public void addSession(AddSessionRequest addSessionRequest) {
        Hall hall = hallConnectorInterface.findById(addSessionRequest.getHallId());
        Concert concert = concertConnectorInterface.findById(addSessionRequest.getConcertId());


        sessionConnectorInterface.addSession(hall, concert, addSessionRequest.getStartDateTime(), addSessionRequest.getEndDateTime(), addSessionRequest.getValidSeatCount());
    }

    public List<SessionResponse> getAllSessions() {
        List<Session> sessions = sessionConnectorInterface.getAllSessions();
        return sessions.stream()
                .map(session -> new SessionResponse(session))
                .collect(Collectors.toList());
    }
}
