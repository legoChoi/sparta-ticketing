package com.sparta.ticketing.controller.hall;

import com.sparta.ticketing.dto.HallRequest;
import com.sparta.ticketing.dto.HallResponse;
import com.sparta.ticketing.service.hall.HallService;
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

    @PostMapping
    public ResponseEntity<HallResponse> addHall(@RequestBody HallRequest hallRequest) {
        HallResponse hallResponse = hallService.addHall(hallRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(hallResponse);
    }
}
