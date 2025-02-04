package com.sparta.ticketing.service.users;

import com.sparta.ticketing.dto.users.UsersRequest;
import com.sparta.ticketing.entity.Users;
import org.springframework.stereotype.Component;

@Component
public interface UsersConnectInterface {
    Users addUsers(UsersRequest usersRequest);
}
