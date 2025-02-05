package com.sparta.ticketing.service.auth;

import com.sparta.ticketing.dto.auth.JwtResponse;
import com.sparta.ticketing.dto.auth.LoginAdminRequest;
import com.sparta.ticketing.dto.auth.LoginUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final AuthConnectorInterface authConnectorInterface;

    public JwtResponse loginUser(LoginUserRequest loginUserRequest) {
        return authConnectorInterface.loginUser(loginUserRequest);
    }


    public JwtResponse loginAdmin(LoginAdminRequest loginAdminRequest) {
        return authConnectorInterface.loginAdmin(loginAdminRequest);
    }

}
