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
    @Enumerated
    private ReservationStatus status;

    private String userName;

    @Setter
    @JoinColumn(name = "concert_session_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Session sessions;

    @Setter
    @JoinColumn(name = "reservated_seat_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SessionSeats sessionSeats;

    public Reservation(ReservationStatus status, String userName) {
        this.status = status;
        this.userName = userName;
    }
}
