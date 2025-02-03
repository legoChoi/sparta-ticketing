package com.sparta.ticketing.service.session;

import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface SessionConnectorInterface {
    void addSession(Hall hall, Concert concert, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Session> getAllSessions();
}
