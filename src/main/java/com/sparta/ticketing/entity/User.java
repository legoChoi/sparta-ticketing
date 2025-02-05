package com.sparta.ticketing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private UserStatus userStatus;

    private final UserRole userRole = UserRole.ROLE_USER;

    private boolean isDeleted;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.userStatus = UserStatus.BRONZE;
        this.isDeleted = false;
    }

    public static User from(String name, String encodedPassword) {
        return new User(name,encodedPassword);
    }

    public void updateUserStatus(UserStatus status) {
        userStatus = status;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePassword(String encodePassword) {
        this.password = password;
    }

    public void deleteUser() {
        this.isDeleted = true;
    }
}
