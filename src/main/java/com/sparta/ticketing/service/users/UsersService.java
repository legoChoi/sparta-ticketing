package com.sparta.ticketing.service.users;


import com.sparta.ticketing.config.PasswordEncoder;
import com.sparta.ticketing.dto.users.UsersRequest;
import com.sparta.ticketing.dto.users.UsersResponse;
import com.sparta.ticketing.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersConnectInterface usersConnectInterface;

    @Transactional
    public UsersResponse addUser(UsersRequest usersRequest) {
        User users = usersConnectInterface.addUsers(usersRequest);

        return UsersResponse.from(users);
    }


    public UsersResponse getUser(Long id) {
        User users = usersConnectInterface.getUser(id);
        return UsersResponse.from(users);
    }


    public UsersResponse updateUser(UsersRequest usersRequest) {
        // 비번검증을 필터로 처리하면될듯
        User updateUser = usersConnectInterface.updateUser(usersRequest);
        return UsersResponse.from(updateUser);
    }

    public void deleteUser(Long id) {
        usersConnectInterface.deleteUser(id);
    }

    private void extracted(String originalPassword, Long id) {
        User users = usersConnectInterface.findById(id);
        if (!users.getPassword().equals(originalPassword)) {
            throw new RuntimeException("잘못된 비번입니다");
        }
    }
}
