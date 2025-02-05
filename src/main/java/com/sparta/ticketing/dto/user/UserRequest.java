package com.sparta.ticketing.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserRequest {

    private String name;

    private String password;

    public UserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @JsonCreator
    public static UserRequest from(
            @JsonProperty("name") String name,
            @JsonProperty("password") String password) {
        return new UserRequest(name, password);
    }
}
