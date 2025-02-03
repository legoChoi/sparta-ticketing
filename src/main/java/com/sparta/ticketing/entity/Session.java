package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;
    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @Column(name = "valid_seat_count")
    private Integer validSeatCount;

    public Session(Hall hall, Concert concert, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer validSeatCount){
        this.hall = hall;
        this.concert = concert;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.validSeatCount = validSeatCount;
    }

    public void countPlusMinus(boolean isPlus) {
        if(isPlus) { ++this.validSeatCount; }
        else { --this.validSeatCount; }
    }
}
