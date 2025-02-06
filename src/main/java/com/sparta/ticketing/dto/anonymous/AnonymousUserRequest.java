package com.sparta.ticketing.dto.anonymous;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AnonymousUserRequest {

    @NotBlank
    @Size(min = 2,max = 10)
    private final String nickname;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "비밀번호는 8~20자의 영문자와 숫자를 포함해야 합니다.")
    private final String password;

    public AnonymousUserRequest(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @JsonCreator
    public static AnonymousUserRequest from(
            @JsonProperty("nickname") String nickname,
            @JsonProperty("password") String password
    ) {
        return new AnonymousUserRequest(nickname, password);
    }
}
