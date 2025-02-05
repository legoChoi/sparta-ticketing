package com.sparta.ticketing.repository.user;

import com.sparta.ticketing.config.PasswordEncoder;
import com.sparta.ticketing.dto.user.UserRequest;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConnectorInterfaceImpl implements UserConnectInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUsers(UserRequest usersRequest) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User updateUser(UserRequest usersRequest) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
