package com.sparta.ticketing.controller.concert;

import com.sparta.ticketing.dto.concert.AddConcertRequest;
import com.sparta.ticketing.dto.concert.ConcertResponse;
import com.sparta.ticketing.dto.concert.GetBestConcertResponse;
import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.service.concert.ConcertService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
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
    public ResponseEntity<String> addConcert(@Valid @RequestBody AddConcertRequest addConcertRequest){
        concertService.addConcert(addConcertRequest);
        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<ConcertResponse> getAllConcerts(){
        return ResponseEntity.ok(concertService.getAllConcerts());
    }

    @GetMapping("/{concertId}")
    public ResponseEntity<GetConcertResponse> getConcert(@PathVariable Long concertId) {
        return ResponseEntity.ok(concertService.getConcert(concertId));
    }

    @GetMapping("/best")
    public ResponseEntity<List<GetBestConcertResponse>> getBestConcerts(
        @Valid @Max(10) @RequestParam int size
    ) {
        return ResponseEntity.ok(concertService.getBsetConcerts(size));
    }

    @GetMapping("/search/v1")
    public ResponseEntity<List<GetConcertResponse>> searchConcert(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(concertService.searchConcert(name, page, size));
    }

    @GetMapping("/search/v2")
    public ResponseEntity<List<GetConcertResponse>> cachingSearchConcert(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(concertService.cachingSearchConcert(name, page, size));
    }
}
