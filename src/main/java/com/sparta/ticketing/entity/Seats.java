package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@NoArgsConstructor
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

    @Column(name = "seat_number")
    private int seatNumber;

    private boolean isAvailable = true;

    public Seats(Session session,int seatNumber) {
        this.session = session ;
        this.seatNumber = seatNumber;
    }

    public static Seats from(Session session, int seatNumber) {
        return new Seats(session, seatNumber);
    }

    public void swapAvailability() { this.isAvailable = !this.isAvailable; }
}
