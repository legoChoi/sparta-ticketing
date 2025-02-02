package com.sparta.ticketing.controller.hall;

import com.sparta.ticketing.dto.hall.AddHallRequest;
import com.sparta.ticketing.dto.hall.AddHallResponse;
import com.sparta.ticketing.dto.hall.HallRequest;
import com.sparta.ticketing.dto.hall.HallResponse;
import com.sparta.ticketing.dto.seats.AllSeatsResponse;
import com.sparta.ticketing.dto.seats.SeatsResponse;
import com.sparta.ticketing.service.hall.HallService;
import com.sparta.ticketing.service.seats.SeatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;
    private final SeatsService seatsService;

    @PostMapping
    public ResponseEntity<AddHallResponse> addHall(@RequestBody AddHallRequest addHallRequest) {
        HallResponse hallResponse = hallService.addHall(HallRequest.from(addHallRequest));
        List<AllSeatsResponse> allSeatsResponses = seatsService.addSeats(hallResponse.getId(), addHallRequest.getSeatNumber());
        AddHallResponse from = AddHallResponse.from(hallResponse, allSeatsResponses);
        return ResponseEntity.status(HttpStatus.CREATED).body(from);
    }

    @GetMapping
    public ResponseEntity<List<HallResponse>> getAllHall() {
        List<HallResponse> allHall = hallService.getAllHall();
        return ResponseEntity.status(HttpStatus.OK).body(allHall);
    }
}
