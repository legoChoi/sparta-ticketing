package com.sparta.ticketing.service.hall;

import com.sparta.ticketing.dto.HallRequest;
import com.sparta.ticketing.entity.Hall;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HallConnectorInterface {
    Hall addHall(HallRequest hallRequest);

    List<Hall> getAllHall();
}
