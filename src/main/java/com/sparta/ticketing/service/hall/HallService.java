package com.sparta.ticketing.service.hall;

import com.sparta.ticketing.dto.hall.AddHallRequest;
import com.sparta.ticketing.dto.hall.HallResponse;
import com.sparta.ticketing.entity.Hall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallConnectorInterface hallConnectorInterface;

    public HallResponse addHall(AddHallRequest hallRequest) {
        Hall hall = hallConnectorInterface.addHall(hallRequest);
        return HallResponse.from(hall);
    }

    public List<HallResponse> getAllHall() {
        List<Hall> allHall = hallConnectorInterface.getAllHall();
        return allHall.stream().map(HallResponse::from).toList();
    }
}
