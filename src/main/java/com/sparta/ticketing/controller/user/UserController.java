package com.sparta.ticketing.controller.user;

import com.sparta.ticketing.dto.user.UserRequest;
import com.sparta.ticketing.dto.user.UserUpdateRequest;
import com.sparta.ticketing.dto.user.UserResponse;
import com.sparta.ticketing.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService usersService;

    @PostMapping
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRequest usersRequest) {
        UserResponse usersResponse = usersService.addUser(usersRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersResponse);
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser() {
        //jwt 에서 id 받을 예정
        Long id = 1L;
        UserResponse user = usersService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @PatchMapping
    public ResponseEntity<UserResponse> updateUser(
            @Valid @RequestBody UserRequest usersRequest,
            HttpServletRequest httpServletRequest
            ) {
        Long id = (Long) httpServletRequest.getAttribute("userId");
        UserUpdateRequest from = UserUpdateRequest.from(id, usersRequest.getName(), usersRequest.getPassword());
        UserResponse usersResponse = usersService.updateUser(from);
        return ResponseEntity.status(HttpStatus.OK).body(usersResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        Long id = 1L;
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
