package com.sparta.ticketing.dto.users;

import com.sparta.ticketing.entity.Users;
import com.sparta.ticketing.entity.UsersStatus;
import lombok.Getter;

@Getter
public class UsersResponse {

    private final String name;
    private final UsersStatus usersStatus;

    public UsersResponse(String name, UsersStatus usersStatus) {
        this.name = name;
        this.usersStatus = usersStatus;
    }

    public static UsersResponse from(Users users) {
        return new UsersResponse(users.getName(), users.getUsersStatus());
    }
}
