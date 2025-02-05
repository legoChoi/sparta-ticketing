package com.sparta.ticketing.service.user;


import com.sparta.ticketing.dto.user.UserRequest;
import com.sparta.ticketing.dto.user.UserResponse;
import com.sparta.ticketing.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserConnectInterface userConnectInterface;

    @Transactional
    public UserResponse addUser(UserRequest usersRequest) {
        User users = userConnectInterface.addUsers(usersRequest);

        return UserResponse.from(users);
    }


    public UserResponse getUser(Long id) {
        User users = userConnectInterface.getUser(id);
        return UserResponse.from(users);
    }


    public UserResponse updateUser(UserRequest usersRequest) {
        // 비번검증을 필터로 처리하면될듯
        User updateUser = userConnectInterface.updateUser(usersRequest);
        return UserResponse.from(updateUser);
    }

    public void deleteUser(Long id) {
        userConnectInterface.deleteUser(id);
    }

    private void extracted(String originalPassword, Long id) {
        User users = userConnectInterface.findById(id);
        if (!users.getPassword().equals(originalPassword)) {
            throw new RuntimeException("잘못된 비번입니다");
        }
    }
}
