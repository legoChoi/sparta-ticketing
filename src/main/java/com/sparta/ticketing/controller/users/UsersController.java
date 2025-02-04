package com.sparta.ticketing.controller.users;

import com.sparta.ticketing.dto.users.UsersGetResponse;
import com.sparta.ticketing.dto.users.UsersRequest;
import com.sparta.ticketing.dto.users.UsersResponse;
import com.sparta.ticketing.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<UsersGetResponse> getUser() {
        //jwt 에서 id 받을 예정
        Long id= 1L;
        UsersGetResponse user = usersService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
