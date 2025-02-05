package com.sparta.ticketing.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserUpdateRequest {

    private String originalPassword;

    private String name;

    private String password;

    public UserUpdateRequest(String originalPassword, String name, String password) {
        this.name = name;
        this.password = password;
        this.originalPassword = originalPassword;
    }

    @JsonCreator
    public static UserUpdateRequest from(
            @JsonProperty("originalPassword") String originalPassword,
            @JsonProperty("name") String name,
            @JsonProperty("password") String password) {
        return new UserUpdateRequest(originalPassword,name, password);
    }
}
