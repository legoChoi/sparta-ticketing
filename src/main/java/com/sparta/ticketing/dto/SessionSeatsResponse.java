package com.sparta.ticketing.dto;

import com.sparta.ticketing.entity.SessionSeats;
import lombok.Getter;

@Getter
public class SessionSeatsResponse {
    private final long sessionSeatsId;
    private final String sessionSeatsStatus;

    public SessionSeatsResponse(long sessionSeatsId,String sessionSeatsStatus) {
        this.sessionSeatsId = sessionSeatsId;
        this.sessionSeatsStatus = sessionSeatsStatus;
    }

    public static SessionSeatsResponse from(SessionSeats sessionSeats) {
        return new SessionSeatsResponse(
                sessionSeats.getId(),
                sessionSeats.getSessionSeatsStatus().name()
        );
    }
}
