package com.sparta.ticketing.controller.users;

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
    public ResponseEntity<UsersResponse> getUser() {
        //jwt 에서 id 받을 예정
        Long id = 1L;
        UsersResponse user = usersService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @PatchMapping
    public ResponseEntity<UsersResponse> updateUser(@RequestBody UsersRequest usersRequest) {
        UsersResponse usersResponse = usersService.updateUser(usersRequest);
        return ResponseEntity.status(HttpStatus.OK).body(usersResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        Long id = 1L;
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
