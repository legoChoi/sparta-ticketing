//package com.sparta.ticketing.service.concert;
//
//import com.sparta.ticketing.annotation.RedisLock;
//import com.sparta.ticketing.dto.concert.AddConcertRequest;
//import com.sparta.ticketing.dto.concert.ConcertResponse;
//import com.sparta.ticketing.dto.concert.GetBestConcertResponse;
//import com.sparta.ticketing.dto.concert.GetConcertResponse;
//import com.sparta.ticketing.entity.Concert;
//import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class RedisConcertService {
//
//    private final ConcertConnectorInterface concertConnectorInterface;
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    @Transactional
//    public void addConcert(AddConcertRequest request) {
//        concertConnectorInterface.addConcert(request.getName());
//        // 패턴 기반 캐시 삭제 수행
//        evictCacheByPattern("searchConcert:*" + request.getName() + "*");
//    }
//
//    public List<GetConcertResponse> cachingSearchConcert(String name, int page, int size) {
//        List<GetConcertResponse> concertResponses = searchAllConcert(name);
//        int start = Math.min(page * size, concertResponses.size());
//        return concertResponses.subList(start, Math.min(start + size, concertResponses.size()));
//    }
//
//    public ConcertResponse getAllConcerts() {
//        List<Concert> concerts = concertConnectorInterface.getAllConcerts();
//        return new ConcertResponse(
//                concerts.stream()
//                        .map(Concert::getName)
//                        .collect(Collectors.toList())
//        );
//    }
//
//    public List<GetBestConcertResponse> getBestConcerts(int size) {
//        return concertConnectorInterface.findBestConcerts(size);
//    }
//
//    @RedisLock(key = "'lock:concert:' + #concertId")
//    public GetConcertResponse getConcert(Long concertId) {
//        Concert concert = concertConnectorInterface.findById(concertId);
//        concert.incrementCount();
//        concertConnectorInterface.updateConcert(concert);
//        return new GetConcertResponse(concertId, concert.getName());
//    }
//
//    public List<GetConcertResponse> searchConcert(String name, int page, int size) {
//        return concertConnectorInterface.searchConcert(name, page, size);
//    }
//
//    @Cacheable(value = "searchConcert", key = "#name")
//    public List<GetConcertResponse> searchAllConcert(String name) {
//        return concertConnectorInterface.searchAllConcert(name);
//    }
//
//    @Transactional
//    public void bulkInsert(int repeats) {
//        List<Concert> concerts = new ArrayList<>();
//        for (int i = 1; i <= repeats; i++) {
//            concerts.add(new Concert("concert" + i));
//        }
//        concertConnectorInterface.bulkInsert(concerts);
//    }
//
//    /**
//     * Redis 캐시 패턴 삭제 메서드
//     */
//    private void evictCacheByPattern(String pattern) {
//        Set<String> keys = redisTemplate.keys(pattern);
//        if (keys != null && !keys.isEmpty()) {
//            redisTemplate.delete(keys);
//        }
//    }
//}
//
