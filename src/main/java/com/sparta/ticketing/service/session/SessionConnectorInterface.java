package com.sparta.ticketing.service.session;

import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionConnectorInterface {
    Session addSession(Hall hall, Concert concert, LocalDateTime startDateTime, LocalDateTime endDateTime, int validSeatCount);

    List<Session> getAllSessions();

    public Session findById(Long sessionId);
}
