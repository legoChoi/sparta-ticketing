package com.sparta.ticketing.controller.auth;

import com.sparta.ticketing.dto.auth.JwtResponse;
import com.sparta.ticketing.dto.auth.LoginUserRequest;
import com.sparta.ticketing.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        JwtResponse jwtResponse = authService.loginUser(loginUserRequest);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }
}
