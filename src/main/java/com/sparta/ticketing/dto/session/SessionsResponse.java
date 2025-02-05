package com.sparta.ticketing.dto.session;

import com.sparta.ticketing.entity.Session;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SessionsResponse {
    private final List<SessionResponse> sessionsResponse;

    public SessionsResponse(List<SessionResponse> sessionsResponse){
        this.sessionsResponse = sessionsResponse;
    }

    public static class SessionResponse{
        private final String concertName;
        private final String location;
        private final LocalDateTime startDateTime;
        private final LocalDateTime endDateTime;

        public SessionResponse(Session session){
            this.concertName = session.getConcert().getName();
            this.location = session.getHall().getLocation();
            this.startDateTime = session.getStartDateTime();
            this.endDateTime = session.getEndDateTime();
        }
    }
}
