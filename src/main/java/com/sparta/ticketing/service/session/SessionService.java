package com.sparta.ticketing.service.session;

import com.sparta.ticketing.dto.session.AddSessionRequest;
import com.sparta.ticketing.dto.session.SessionResponse;
import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.entity.Session;
import com.sparta.ticketing.repository.concert.ConcertRepository;
import com.sparta.ticketing.repository.hall.HallRepository;
import com.sparta.ticketing.repository.session.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionService implements SessionServiceInterface{
    private final SessionRepository sessionRepository;
    private final HallRepository hallRepository;
    private final ConcertRepository concertRepository;

    @Override
    public void addSession(AddSessionRequest addSessionRequest) {
        // TODO: exception 바꾸기
        Hall hall = hallRepository.findById(addSessionRequest.getHallId())
                .orElseThrow(()-> new RuntimeException());

        Concert concert = concertRepository.findById(addSessionRequest.getConcertId())
                        .orElseThrow(() -> new RuntimeException());
        sessionRepository.save(new Session(hall, concert, addSessionRequest.getStartDateTime(), addSessionRequest.getEndDateTime()));
    }

    @Override
    public List<SessionResponse> getAllSessions() {
        List<Session> sessions = sessionRepository.findAll();
        return sessions.stream()
                .map(session -> new SessionResponse(session))
                .collect(Collectors.toList());
    }
}
