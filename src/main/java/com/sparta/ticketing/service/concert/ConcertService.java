package com.sparta.ticketing.service.concert;

import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.entity.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService{
    private final ConcertConnectorInterface concertConnectorInterface;

    @Transactional
    public void addConcert(AddConcertRequest addConcertRequest) {
        concertConnectorInterface.addConcert(addConcertRequest.getName());
    }

    @Transactional(readOnly = true)
    public ConcertResponse getAllConcerts() {
        List<Concert> concerts = concertConnectorInterface.getAllConcerts();
        return new ConcertResponse(
                concerts.stream()
                .map(concert -> concert.getName())
                .collect(Collectors.toList())
        );
    }
}
