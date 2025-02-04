package com.sparta.ticketing.dto.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UsersRequest {

    private String name;
    private String password;

    public UsersRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @JsonCreator
    public static UsersRequest from(
            @JsonProperty("name") String name,
            @JsonProperty("password") String password) {
        return new UsersRequest(name, password);
    }
}
