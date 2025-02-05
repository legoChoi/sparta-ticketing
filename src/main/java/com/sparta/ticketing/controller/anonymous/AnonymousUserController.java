package com.sparta.ticketing.controller.anonymous;

import com.sparta.ticketing.dto.anonymous.AnonymousUserRequest;
import com.sparta.ticketing.dto.anonymous.AnonymousUserResponse;
import com.sparta.ticketing.service.anonymous.AnonymousUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anonymousUser")
@RequiredArgsConstructor
public class AnonymousUserController {

    private final AnonymousUserService anonymousUserService;

    @PostMapping
    public ResponseEntity<AnonymousUserResponse> addAnonymousUser(@RequestBody AnonymousUserRequest anonymousUserRequest) {
        AnonymousUserResponse anonymousUserResponse = anonymousUserService.addAnonymousUser(anonymousUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(anonymousUserResponse);
    }

    @PostMapping("/eliminate")
    public ResponseEntity<Void> deleteAnonymousUser(@RequestBody AnonymousUserRequest anonymousUserRequest) {
        anonymousUserService.deleteAnonymousUser(anonymousUserRequest);
        return ResponseEntity.ok().build();
    }
}
