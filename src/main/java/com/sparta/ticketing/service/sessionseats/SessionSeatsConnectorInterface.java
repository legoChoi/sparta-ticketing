package com.sparta.ticketing.service.sessionseats;

import com.sparta.ticketing.entity.SessionSeats;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SessionSeatsConnectorInterface {
    List<SessionSeats> getSessionSeats();
}
