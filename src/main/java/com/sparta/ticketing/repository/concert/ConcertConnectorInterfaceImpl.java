package com.sparta.ticketing.repository.concert;

import com.sparta.ticketing.dto.concert.GetBestConcertResponse;
import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.service.concert.ConcertConnectorInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConcertConnectorInterfaceImpl implements ConcertConnectorInterface {
    private final ConcertRepository concertRepository;
    private final QueryDslConcertRepository queryDslConcertRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addConcert(String name) {
        concertRepository.save(new Concert(name));
    }

    @Override
    public void bulkInsert(List<Concert> concerts) {
        jdbcTemplate.batchUpdate(
            "insert into concert (concert_name, search_count) values (?, ?)",
            concerts,
            1000,
            (ps, concert) -> {
                ps.setString(1, concert.getName());
                ps.setInt(2, concert.getSearchCount());
            }
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetBestConcertResponse> findBestConcerts(int size) {
        return concertRepository.findConcertOrderBySearchCount(PageRequest.of(0, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Concert findById(Long concertId) {
        return concertRepository.findById(concertId).orElseThrow(() -> new IllegalArgumentException("not found concert"));
    }

    @Override
    public List<GetConcertResponse> searchAllConcert(String name) {
        return queryDslConcertRepository.searchConcert(name, null);
    }


    @Override
    public List<GetConcertResponse> searchConcert(String name, int page, int size) {
        return queryDslConcertRepository.searchConcert(name, PageRequest.of(page, size));
    }

    @Override
    public void updateConcert(Concert concert) {
        concertRepository.save(concert);
    }
}
