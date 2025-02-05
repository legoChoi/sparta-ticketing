package com.sparta.ticketing.dto.user;

import lombok.Getter;

@Getter
public class UserUpdateRequest {

    private Long id;

    private String name;

    private String password;

    public UserUpdateRequest(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public static UserUpdateRequest from(Long id, String name,String password) {
        return new UserUpdateRequest(id,name, password);
    }
}
