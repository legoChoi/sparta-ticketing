package com.sparta.ticketing.controller.concert;

import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.service.concert.ConcertServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/concerts")
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertServiceInterface concertService;

    @PostMapping
    public ResponseEntity<String> addConcert(@RequestBody AddConcertRequest addConcertRequest){
        concertService.addConcert(addConcertRequest);

        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<List<ConcertResponse>> getAllConcerts(){
        return ResponseEntity.ok(concertService.getAllConcerts());
    }
}
