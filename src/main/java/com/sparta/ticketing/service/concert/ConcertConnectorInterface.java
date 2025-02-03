package com.sparta.ticketing.service.concert;

import com.sparta.ticketing.entity.Concert;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ConcertConnectorInterface {
    void addConcert(String name);

    List<Concert> getAllConcerts();

    Concert findById(Long concertId);
}
