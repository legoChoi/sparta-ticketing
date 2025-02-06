package com.sparta.ticketing.controller.auth;

import com.sparta.ticketing.dto.auth.JwtResponse;
import com.sparta.ticketing.dto.auth.LoginAdminRequest;
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
@RequestMapping("/adminAuth")
@RequiredArgsConstructor
public class AdminAuthController {
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<JwtResponse> loginAdmin(@Valid @RequestBody LoginAdminRequest loginAdminRequest) {
        JwtResponse jwtResponse = authService.loginAdmin(loginAdminRequest);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }
}
