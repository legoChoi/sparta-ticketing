package com.sparta.ticketing.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginUserRequest {

    @NotBlank
    @Size(min = 6,max = 20)
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "비밀번호는 8~20자의 영문자와 숫자를 포함해야 합니다.")
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
