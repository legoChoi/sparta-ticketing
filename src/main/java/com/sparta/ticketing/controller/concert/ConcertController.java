package com.sparta.ticketing.controller.concert;

import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.service.concert.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<ConcertResponse>> getAllConcerts(){
        return ResponseEntity.ok(concertService.getAllConcerts());
    }

    @GetMapping("/{concertId}")
    public ResponseEntity<GetConcertResponse> getConcert(@PathVariable Long concertId) {
        return ResponseEntity.ok(concertService.getConcert(concertId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetConcertResponse>> searchConcert(
        @RequestParam String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(concertService.searchConcert(name, page, size));
    }
}
