package com.sparta.ticketing.dto.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AdminRequest {

    private final String adminCode;

    private final String password;

    public AdminRequest(String adminCode, String password) {
        this.adminCode = adminCode;
        this.password = password;
    }

    @JsonCreator
    public static AdminRequest from(
            @JsonProperty("adminCode") String adminCode,
            @JsonProperty("password") String password) {
        return new AdminRequest(adminCode, password);
    }
}
