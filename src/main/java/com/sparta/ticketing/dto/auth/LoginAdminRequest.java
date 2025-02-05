package com.sparta.ticketing.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginAdminRequest {
    private final String adminCode;
    private final String password;

    public LoginAdminRequest(String adminCode, String password) {
        this.adminCode = adminCode;
        this.password = password;
    }

    @JsonCreator
    public static LoginAdminRequest from(
            @JsonProperty("adminCode")String adminCode,
            @JsonProperty("password")String password
    ) {
        return new LoginAdminRequest(adminCode, password);
    }
}
