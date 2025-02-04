package com.sparta.ticketing.controller.users;

import com.sparta.ticketing.dto.users.UsersRequest;
import com.sparta.ticketing.dto.users.UsersResponse;
import com.sparta.ticketing.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<UsersResponse> addUser(@RequestBody UsersRequest usersRequest) {
        UsersResponse usersResponse = usersService.addUser(usersRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersResponse);
    }
}
