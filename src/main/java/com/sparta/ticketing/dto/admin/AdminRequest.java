package com.sparta.ticketing.dto.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AdminRequest {

    @NotBlank
    @Size(min = 5,max = 20)
    private final String adminCode;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "비밀번호는 8~20자의 영문자와 숫자를 포함해야 합니다.")
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
