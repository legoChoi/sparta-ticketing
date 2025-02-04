package com.sparta.ticketing.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private UsersStatus usersStatus;

    public Users(String name, String password) {
        this.name = name;
        this.password = password;
        this.usersStatus = UsersStatus.BRONZE;
    }

    public static Users from() {
        return new Users();
    }

    public void updateUserStatus(UsersStatus status) {
        usersStatus = status;
    }
}
