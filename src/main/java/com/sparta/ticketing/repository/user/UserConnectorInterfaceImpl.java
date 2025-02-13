package com.sparta.ticketing.repository.user;

import com.sparta.ticketing.config.PasswordEncoder;
import com.sparta.ticketing.dto.user.UserRequest;
import com.sparta.ticketing.dto.user.UserUpdateRequest;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserConnectorInterfaceImpl implements UserConnectInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User addUsers(UserRequest usersRequest) {
        String encodedPassword = passwordEncoder.encode(usersRequest.getPassword());
        User user = User.from(usersRequest.getName(), encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return findById(id);
    }

    @Override
    @Transactional
    public User updateUser(UserUpdateRequest usersRequest) {
        User user = findById(usersRequest.getId());
        if (!user.getName().equals(usersRequest.getName())) {
            user.updateName(usersRequest.getName());
        }

        if (!passwordEncoder.matches(usersRequest.getPassword(), user.getPassword())) {
            String encodePassword = passwordEncoder.encode(usersRequest.getPassword());
            user.updatePassword(encodePassword);
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findById(id);
        user.deleteUser();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionStatus.NOTFOUND_USER.getMessage()));
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(()-> new IllegalArgumentException(ExceptionStatus.NOTFOUND_USER.getMessage()));
    }
}
