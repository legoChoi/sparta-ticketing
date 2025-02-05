package com.sparta.ticketing.dto.admin;

import lombok.Getter;

@Getter
public class UpdateAdminRequest {

    private final String adminCode;
    private final String password;
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
