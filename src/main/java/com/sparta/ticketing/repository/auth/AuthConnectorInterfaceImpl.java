package com.sparta.ticketing.repository.auth;

import com.sparta.ticketing.dto.auth.JwtResponse;
import com.sparta.ticketing.dto.auth.LoginAdminRequest;
import com.sparta.ticketing.dto.auth.LoginUserRequest;
import com.sparta.ticketing.service.auth.AuthConnectorInterface;
import org.springframework.stereotype.Component;

@Component
public class AuthConnectorInterfaceImpl implements AuthConnectorInterface {
    @Override
    public JwtResponse loginUser(LoginUserRequest loginUserRequest) {
        return null;
    }

    @Override
    public JwtResponse loginAdmin(LoginAdminRequest loginAdminRequest) {
        return null;
    }
}
