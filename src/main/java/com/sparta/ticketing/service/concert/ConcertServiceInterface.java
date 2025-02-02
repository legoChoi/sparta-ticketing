package com.sparta.ticketing.service.concert;

import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConcertServiceInterface {
    void addConcert(AddConcertRequest addConcertRequest);

    List<ConcertResponse> getAllConcerts();
}
