package com.sparta.ticketing.service.sessionseats;

import com.sparta.ticketing.dto.SessionSeatsResponse;
import com.sparta.ticketing.entity.SessionSeats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionSeatsService {
    private final SessionSeatsConnectorInterface sessionSeatsConnectorInterface;


    public List<SessionSeatsResponse> getSessionSeats() {
        List<SessionSeats> sessionSeats = sessionSeatsConnectorInterface.getSessionSeats();
        return sessionSeats.stream().map(SessionSeatsResponse::from).toList();
    }
}
