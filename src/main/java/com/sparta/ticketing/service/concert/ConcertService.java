package com.sparta.ticketing.service.concert;

import com.sparta.ticketing.annotation.RedisLock;
import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.entity.Concert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConcertService{
    private final ConcertConnectorInterface concertConnectorInterface;

    @Transactional
    public void addConcert(AddConcertRequest addConcertRequest) {
        concertConnectorInterface.addConcert(addConcertRequest.getName());
    }

    public List<ConcertResponse> getAllConcerts() {
        List<Concert> concerts = concertConnectorInterface.getAllConcerts();
        return concerts.stream()
            .map(concert -> new ConcertResponse(concert.getName()))
            .collect(Collectors.toList());
    }

    @RedisLock(key = "'lock:concert:' + #concertId")
    public GetConcertResponse getConcert(Long concertId) {
        Concert concert = concertConnectorInterface.findById(concertId);
        concert.incrementCount();
        concertConnectorInterface.updateConcert(concert);
        return new GetConcertResponse(concertId, concert.getName());
    }

    public List<GetConcertResponse> searchConcert(String name, int page, int size) {
        return concertConnectorInterface.searchConcert(name, page, size);
    }

    public void bulkInsert(int repeats) {
        List<Concert> concerts = new ArrayList<>();
        for(int i = 1; i <= repeats; i++) {
            concerts.add(new Concert("concert" + i));
        }
        concertConnectorInterface.bulkInsert(concerts);
    }
}
