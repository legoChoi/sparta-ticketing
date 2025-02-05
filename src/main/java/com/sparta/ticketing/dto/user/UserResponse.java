package com.sparta.ticketing.dto.user;

import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.entity.UserStatus;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String name;
    private final UserStatus userStatus;

    public UserResponse(String name, UserStatus userStatus) {
        this.name = name;
        this.userStatus = userStatus;
    }

    public static UserResponse from(User users) {
        return new UserResponse(users.getName(), users.getUserStatus());
    }
}
