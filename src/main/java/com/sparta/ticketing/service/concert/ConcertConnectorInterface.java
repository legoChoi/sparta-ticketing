package com.sparta.ticketing.service.concert;

import com.sparta.ticketing.dto.concert.GetBestConcertResponse;
import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.entity.Concert;

import java.util.List;

public interface ConcertConnectorInterface {
    void addConcert(String name);

    void bulkInsert(List<Concert> concerts);

    List<Concert> getAllConcerts();

    Concert findById(Long concertId);

    List<GetConcertResponse> searchAllConcert(String name);

    List<GetConcertResponse> searchConcert(String name, int page, int size);

    void updateConcert(Concert concert);

    List<GetBestConcertResponse> findBestConcerts(int size);
}
