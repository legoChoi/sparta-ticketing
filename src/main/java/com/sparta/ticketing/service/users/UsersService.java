package com.sparta.ticketing.service.users;


import com.sparta.ticketing.dto.users.UsersRequest;
import com.sparta.ticketing.dto.users.UsersResponse;
import com.sparta.ticketing.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersConnectInterface usersConnectInterface;

    public UsersResponse addUser(UsersRequest usersRequest) {
        Users users = usersConnectInterface.addUsers(usersRequest);

        return UsersResponse.from(users);
    }


}
