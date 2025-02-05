package com.sparta.ticketing.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginUserRequest {

    private String name;
    private String password;

    public LoginUserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @JsonCreator
    public static LoginUserRequest from(
            @JsonProperty("name") String name,
            @JsonProperty("password") String password
    ) {
        return new LoginUserRequest(name, password);
    }
}
