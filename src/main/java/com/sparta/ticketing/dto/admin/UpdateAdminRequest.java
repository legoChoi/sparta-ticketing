package com.sparta.ticketing.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateAdminRequest {

    @NotBlank
    @Size(min = 5,max = 20)
    private final String adminCode;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
            message = "비밀번호는 8~20자의 영문자와 숫자를 포함해야 합니다.")
    private final String password;

    @NotNull
    private final long id;

    public UpdateAdminRequest(String adminCode, String password, long id) {
        this.adminCode = adminCode;
        this.password = password;
        this.id = id;
    }

    public static UpdateAdminRequest from(AdminRequest adminRequest, Long id) {
        return new UpdateAdminRequest(
                adminRequest.getAdminCode(),
                adminRequest.getPassword(),
                id
                );
    }
}
