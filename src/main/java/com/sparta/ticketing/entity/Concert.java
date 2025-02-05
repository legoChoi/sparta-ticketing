package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "concerts")
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "concert_name")
    private String name;

    @Column(name = "search_count")
    private int searchCount;

    public Concert(String name) {
        this.name = name;
        this.searchCount = 0;
    }

    public void incrementCount() { ++this.searchCount; }
}