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

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.userStatus = UserStatus.BRONZE;
    }

    public static User from() {
        return new User();
    }

    public void updateUserStatus(UserStatus status) {
        userStatus = status;
    }
}
