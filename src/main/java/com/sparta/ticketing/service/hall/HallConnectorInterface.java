package com.sparta.ticketing.service.hall;

import com.sparta.ticketing.dto.hall.AddHallRequest;
import com.sparta.ticketing.entity.Hall;

import java.util.List;

public interface HallConnectorInterface {
    Hall addHall(AddHallRequest hallRequest);

    List<Hall> getAllHall();

    Hall findById(long hallId);
}
