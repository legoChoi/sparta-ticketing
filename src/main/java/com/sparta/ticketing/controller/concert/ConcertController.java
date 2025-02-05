package com.sparta.ticketing.controller.concert;

import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.service.concert.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;

    @PostMapping
    public ResponseEntity<String> addConcert(@RequestBody AddConcertRequest addConcertRequest){
        concertService.addConcert(addConcertRequest);
        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<ConcertResponse> getAllConcerts(){
        return ResponseEntity.ok(concertService.getAllConcerts());
    }
}
