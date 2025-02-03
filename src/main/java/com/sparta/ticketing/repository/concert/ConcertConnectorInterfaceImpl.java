package com.sparta.ticketing.repository.concert;

import com.sparta.ticketing.entity.Concert;
import com.sparta.ticketing.service.concert.ConcertConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Component
@RequiredArgsConstructor
public class ConcertConnectorInterfaceImpl implements ConcertConnectorInterface {
    private final ConcertRepository concertRepository;

    @Transactional
    @Override
    public void addConcert(String name) {
        concertRepository.save(new Concert(name));
    }

    @Override
    public List<Concert> getAllConcerts() {
        return concertRepository.findAll();
    }

    @Override
    public Concert findById(Long concertId) {
        return concertRepository.findById(concertId).orElseThrow(() ->new IllegalArgumentException("not found concert"));
    }
}
