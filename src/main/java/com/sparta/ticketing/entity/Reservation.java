package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @Setter
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private String userName;
    //여기 유저entity로 변경 예정

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    @JoinColumn(name = "seats_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Seat seats;

    private Reservation(ReservationStatus status, String userName, Session session, Seat seats) {
        this.status = status;
        this.userName = userName;
        this.session = session;
        this.seats = seats;
    }

    public static Reservation from(ReservationStatus status, String userName, Session session, Seat seats) {
        return new Reservation(status, userName, session, seats);
    }
}
