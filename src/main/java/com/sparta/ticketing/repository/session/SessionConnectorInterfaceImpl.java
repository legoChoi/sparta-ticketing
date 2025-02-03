package com.sparta.ticketing.repository.session;

import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.service.session.SessionConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SessionConnectorInterfaceImpl implements SessionConnectorInterface {
    private final SessionRepository sessionRepository;

    @Transactional
    @Override
    public Session addSession(Hall hall, Concert concert, LocalDateTime startDateTime, LocalDateTime endDateTime, int validSeatCount) {
        return sessionRepository.save(new Session(hall, concert, startDateTime, endDateTime, validSeatCount));
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session findById(Long sessionId) {
        return sessionRepository.findFirstById(sessionId)
            .orElseThrow(() -> new IllegalArgumentException("no session found"));
    }

    @Override
    public Session update(Session session) {
        return sessionRepository.save(session);
    }
}
