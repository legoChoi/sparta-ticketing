package com.sparta.ticketing.service.hall;

import com.sparta.ticketing.dto.HallRequest;
import com.sparta.ticketing.entity.Hall;
import org.springframework.stereotype.Component;

@Component
public interface HallConnectorInterface {
    Hall addHall(HallRequest hallRequest);
}
