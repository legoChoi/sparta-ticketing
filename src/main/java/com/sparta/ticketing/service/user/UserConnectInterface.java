package com.sparta.ticketing.service.user;

import com.sparta.ticketing.dto.user.UserRequest;
import com.sparta.ticketing.dto.user.UserUpdateRequest;
import com.sparta.ticketing.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserConnectInterface {
    User addUsers(UserRequest usersRequest);

    User getUser(Long id);

    User updateUser(UserUpdateRequest usersRequest);

    User findById(Long id);

    void deleteUser(Long id);
}
