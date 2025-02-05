package com.sparta.ticketing.dto.users;

import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.entity.UserStatus;
import lombok.Getter;

@Getter
public class UsersResponse {

    private final String name;
    private final UserStatus userStatus;

    public UsersResponse(String name, UserStatus userStatus) {
        this.name = name;
        this.userStatus = userStatus;
    }

    public static UsersResponse from(User users) {
        return new UsersResponse(users.getName(), users.getUserStatus());
    }
}
