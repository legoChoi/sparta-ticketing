package com.sparta.ticketing.service.hall;

import com.sparta.ticketing.dto.hall.HallRequest;
import com.sparta.ticketing.entity.Hall;
import org.springframework.stereotype.Component;

import java.util.List;

public interface HallConnectorInterface {
    Hall addHall(HallRequest hallRequest);

    List<Hall> getAllHall();

    Hall findById(long hallId);
}
