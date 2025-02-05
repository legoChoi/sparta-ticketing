package com.sparta.ticketing.service.auth;

import com.sparta.ticketing.dto.auth.JwtResponse;
import com.sparta.ticketing.dto.auth.LoginAdminRequest;
import com.sparta.ticketing.dto.auth.LoginUserRequest;
import org.springframework.stereotype.Component;

@Component
public interface AuthConnectorInterface {
    JwtResponse loginUser(LoginUserRequest loginUserRequest);

    JwtResponse loginAdmin(LoginAdminRequest loginAdminRequest);
}
