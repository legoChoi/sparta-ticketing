package com.sparta.ticketing.service.hall;

import com.sparta.ticketing.dto.HallRequest;
import com.sparta.ticketing.dto.HallResponse;
import com.sparta.ticketing.entity.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallConnectorInterface hallConnectorInterface;

    public HallResponse addHall(HallRequest hallRequest) {
        Hall hall = hallConnectorInterface.addHall(hallRequest);
        return HallResponse.from(hall);
    }
}
