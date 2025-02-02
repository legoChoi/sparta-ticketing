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

    @Setter
    @JoinColumn(name = "session_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    @Setter
    @JoinColumn(name = "seats_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Seats seats;

    public Reservation(ReservationStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }
}
