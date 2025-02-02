package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @Column(name = "seat_number")
    private int seatNumber;


    public Seats(Hall hall,int seatNumber) {
        this.hall = hall;
        this.seatNumber = seatNumber;
    }

    public static Seats from(Hall hall, int seatNumber) {
        return new Seats(hall, seatNumber);
    }
}
