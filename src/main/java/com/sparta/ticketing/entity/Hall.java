package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hall_name")
    private String name;
    @Column(name = "location")
    private String location;

    public Hall(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public static Hall from(String name, String location) {
        return new Hall(name, location);
    }
}
