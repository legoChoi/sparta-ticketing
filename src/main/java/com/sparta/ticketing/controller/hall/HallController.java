package com.sparta.ticketing.controller.hall;

import com.sparta.ticketing.dto.*;
import com.sparta.ticketing.service.hall.HallService;
import com.sparta.ticketing.service.seats.SeatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;
    private final SeatsService seatsService;

    @PostMapping
    public ResponseEntity<AddHallResponse> addHall(@RequestBody AddHallRequest addHallRequest) {
        HallResponse hallResponse = hallService.addHall(HallRequest.from(addHallRequest));
        SeatsResponse seatsResponse = seatsService.addSeats(addHallRequest.getSeatNumber());
        AddHallResponse from = AddHallResponse.from(hallResponse, seatsResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(from);
    }
}
