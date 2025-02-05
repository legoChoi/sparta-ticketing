package com.sparta.ticketing.dto.admin;

import com.sparta.ticketing.entity.AdminUser;
import lombok.Getter;

@Getter
public class AdminResponse {

    private final String adminCode;

    public AdminResponse(String adminCode) {
        this.adminCode = adminCode;
    }

    public static AdminResponse from(AdminUser admin) {
        return new AdminResponse(admin.getAdminCode());
    }
}
