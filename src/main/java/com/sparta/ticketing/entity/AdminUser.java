package com.sparta.ticketing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adminCode;

    private String password;

    private final UserRole userRole = UserRole.ROLE_ADMIN;

    private final UserStatus userStatus = UserStatus.NONE;

    public AdminUser(String adminCode, String password) {
        this.adminCode = adminCode;
        this.password = password;
    }

    public static AdminUser from(String adminCode, String encodedPassword) {
        return new AdminUser();
    }

    public void updateAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public void updatePassword(String encodePassword) {
        this.password = encodePassword;
    }
}
