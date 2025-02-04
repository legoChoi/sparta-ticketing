package com.sparta.ticketing.dto.users;

import com.sparta.ticketing.entity.Users;
import com.sparta.ticketing.entity.UsersStatus;
import lombok.Getter;

@Getter
public class UsersGetResponse {

    private String name;
    private String password;
    private UsersStatus usersStatus;

    public UsersGetResponse(String name, String password, UsersStatus usersStatus) {
        this.name = name;
        this.password = password;
        this.usersStatus = usersStatus;
    }

    public static UsersGetResponse from(Users users) {
        return new UsersGetResponse(
                users.getName(),
                users.getPassword(),
                users.getUsersStatus());
    }
}
