package com.sparta.ticketing.dto.session;

import com.sparta.ticketing.entity.Session;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class SessionsResponse {
    private List<SessionResponse> sessionsResponse;

    public SessionsResponse(List<SessionResponse> sessionsResponse){
        this.sessionsResponse = sessionsResponse;
    }

    public static class SessionResponse{
        private String concertName;
        private String location;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;

        public SessionResponse(Session session){
            this.concertName = session.getConcert().getName();
            this.location = session.getHall().getLocation();
            this.startDateTime = session.getStartDateTime();
            this.endDateTime = session.getEndDateTime();
        }
    }
}
