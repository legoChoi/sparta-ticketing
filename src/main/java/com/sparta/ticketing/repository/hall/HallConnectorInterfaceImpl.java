package com.sparta.ticketing.repository.hall;

import com.sparta.ticketing.dto.hall.AddHallRequest;
import com.sparta.ticketing.entity.Hall;
import com.sparta.ticketing.service.hall.HallConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class HallConnectorInterfaceImpl implements HallConnectorInterface {

    private final HallRepository hallRepository;

    @Override
    public Hall addHall(AddHallRequest hallRequest) {
        Hall from = Hall.from(hallRequest);
        return hallRepository.save(from);
    }

    @Override
    public List<Hall> getAllHall() {
        return hallRepository.findAll();
    }

    @Override
    public Hall findById(long hallId) {
        return hallRepository.findById(hallId).orElseThrow(()->new IllegalArgumentException("not found hall"));
    }
}
