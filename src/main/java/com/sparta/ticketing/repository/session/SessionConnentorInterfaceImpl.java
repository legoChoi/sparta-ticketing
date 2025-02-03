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

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class SessionConnentorInterfaceImpl implements SessionConnectorInterface {
    private final SessionRepository sessionRepository;

    @Transactional
    @Override
    public void addSession(Hall hall, Concert concert, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        sessionRepository.save(new Session(hall, concert, startDateTime, endDateTime));
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}
