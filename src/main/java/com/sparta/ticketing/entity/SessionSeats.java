package com.sparta.ticketing.entity;

import com.sparta.ticketing.status.SessionSeatsStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SessionSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seats_id")
    private Seats seats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @Enumerated(EnumType.STRING)
    @Column(name = "seats_status")
    private SessionSeatsStatus sessionSeatsStatus;

    public SessionSeats(Seats seats, Session session) {
        this.seats = seats;
        this.session = session;
    }

    public static SessionSeats from(Seats seats, Session session) {
        return new SessionSeats(seats, session);
    }
}
