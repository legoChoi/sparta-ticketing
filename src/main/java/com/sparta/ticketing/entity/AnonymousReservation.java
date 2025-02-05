package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AnonymousReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seats_id")
    private Seat seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anonymous_user_id")
    private AnonymousUser anonymousUser;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    public AnonymousReservation(Session session, Seat seat, AnonymousUser anonymousUser, ReservationStatus reservationStatus) {
        this.session = session;
        this.seat = seat;
        this.anonymousUser = anonymousUser;
        this.reservationStatus = reservationStatus;
    }

    public static AnonymousReservation from() {
        return new AnonymousReservation();
    }
}
