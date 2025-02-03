package com.sparta.ticketing.service.concert;

import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.repository.concert.ConcertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcertService implements ConcertServiceInterface {
    private final ConcertRepository concertRepository;
    private final ConcertConnectorInterface concertConnectorInterface;

    @Transactional
    @Override
    public void addConcert(AddConcertRequest addConcertRequest) {
        concertConnectorInterface.addConcert(addConcertRequest.getName());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ConcertResponse> getAllConcerts() {
        List<Concert> concerts = concertConnectorInterface.getAllConcerts();
        return concerts.stream()
                .map(concert -> new ConcertResponse(concert.getName()))
                .collect(Collectors.toList());
    }
}
