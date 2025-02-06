package com.sparta.ticketing.service.concert;

import com.sparta.ticketing.aop.annotation.CacheEvictPattern;
import com.sparta.ticketing.aop.annotation.RedisCacheEvict;
import com.sparta.ticketing.aop.annotation.RedisLock;
import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.dto.concert.GetBestConcertResponse;
import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.entity.Concert;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConcertService{
    private final ConcertConnectorInterface concertConnectorInterface;

    // @CacheEvictPattern(pattern = "#request.getName()", value = "searchConcert")
    @RedisCacheEvict(key = "#request.getName()")
    @Transactional
    public void addConcert(AddConcertRequest request) {
        concertConnectorInterface.addConcert(request.getName());
    }

//    public List<GetConcertResponse> cachingSearchConcert(String name, int page, int size) {
//        List<GetConcertResponse> concertResponses = searchPagingConcert(name);
//        int listSize = concertResponses.size();
//        int start = page * size;
//
//        if(start > listSize) return new ArrayList<>();
//
//        if(start + size > listSize) { size = listSize - start; }
//        return concertResponses.subList(start, start + size);
//    }

    @Cacheable(value = "searchConcert", key = "#name + '-' + #page + '-' + #size")
    public List<GetConcertResponse> cachingSearchConcert(String name, int page, int size) {
        return concertConnectorInterface.searchConcert(name, page, size);
    }

    public ConcertResponse getAllConcerts() {
        List<Concert> concerts = concertConnectorInterface.getAllConcerts();
        return new ConcertResponse(
                concerts.stream()
                .map(Concert::getName)
                .collect(Collectors.toList())
        );
    }

    public List<GetBestConcertResponse> getBsetConcerts(int size) {
        return concertConnectorInterface.findBestConcerts(size);
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

//    @Cacheable(value = "searchConcert", key = "#name")
//    public List<GetConcertResponse> searchAllConcert(String name) {
//        return concertConnectorInterface.searchAllConcert(name);
//    }

    @Transactional
    public void bulkInsert(int repeats) {
        List<Concert> concerts = new ArrayList<>();
        for(int i = 1; i <= repeats; i++) {
            concerts.add(new Concert(randomString(20)));
        }
        concertConnectorInterface.bulkInsert(concerts);
    }

    private String randomString(int length) {
        String alphabetAndDigits = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            sb.append(alphabetAndDigits.charAt((int)Math.floor(Math.random() * alphabetAndDigits.length())));
        }
        return sb.toString();
    }
}
