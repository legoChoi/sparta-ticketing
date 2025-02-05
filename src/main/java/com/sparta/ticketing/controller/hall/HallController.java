package com.sparta.ticketing.controller.hall;

import com.sparta.ticketing.dto.hall.AddHallRequest;
import com.sparta.ticketing.dto.hall.HallListResponse;
import com.sparta.ticketing.dto.hall.HallResponse;
import com.sparta.ticketing.service.hall.HallService;
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

    @PostMapping
    public ResponseEntity<HallResponse> addHall(@RequestBody AddHallRequest addHallRequest) {
        HallResponse hallResponse = hallService.addHall(addHallRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(hallResponse);
    }

    @GetMapping
    public ResponseEntity<HallListResponse> getAllHall() {
        List<HallResponse> allHall = hallService.getAllHall();
        HallListResponse hallListResponse = HallListResponse.from(allHall);
        return ResponseEntity.status(HttpStatus.OK).body(hallListResponse);
    }
}
