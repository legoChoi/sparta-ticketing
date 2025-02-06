package com.sparta.ticketing.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 6,max = 20)
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "비밀번호는 8~20자의 영문자와 숫자를 포함해야 합니다.")
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
