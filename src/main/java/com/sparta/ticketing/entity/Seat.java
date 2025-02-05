package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @Column(name = "seat_number")
    private int seatNumber;

    private boolean isAvailable = true;

    public Seat(Session session, int seatNumber) {
        this.session = session ;
        this.seatNumber = seatNumber;
    }

    public static Seat from(Session session, int seatNumber) {
        return new Seat(session, seatNumber);
    }

    public void swapAvailability() {
        this.isAvailable = !this.isAvailable;
    }
}
