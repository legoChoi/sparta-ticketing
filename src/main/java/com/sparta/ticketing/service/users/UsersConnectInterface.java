package com.sparta.ticketing.service.users;

import com.sparta.ticketing.dto.users.UsersRequest;
import com.sparta.ticketing.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UsersConnectInterface {
    User addUsers(UsersRequest usersRequest);

    User getUser(Long id);

    User updateUser(UsersRequest usersRequest);

    User findById(Long id);

    void deleteUser(Long id);
}
