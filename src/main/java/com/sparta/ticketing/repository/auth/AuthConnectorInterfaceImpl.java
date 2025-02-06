package com.sparta.ticketing.repository.auth;

import com.sparta.ticketing.config.JwtUtil;
import com.sparta.ticketing.config.PasswordEncoder;
import com.sparta.ticketing.dto.auth.JwtResponse;
import com.sparta.ticketing.dto.auth.LoginAdminRequest;
import com.sparta.ticketing.dto.auth.LoginUserRequest;
import com.sparta.ticketing.entity.AdminUser;
import com.sparta.ticketing.entity.User;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.repository.user.UserConnectorInterfaceImpl;
import com.sparta.ticketing.repository.user.UserRepository;
import com.sparta.ticketing.service.admin.AdminConnectorInterface;
import com.sparta.ticketing.service.auth.AuthConnectorInterface;
import com.sparta.ticketing.service.user.UserConnectInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthConnectorInterfaceImpl implements AuthConnectorInterface {
    private final PasswordEncoder passwordEncoder;
    private final UserConnectInterface userConnectInterface;
    private final AdminConnectorInterface adminConnectorInterface;
    private final JwtUtil jwtUtil;

    @Override
    public JwtResponse loginUser(LoginUserRequest loginUserRequest) {
        User user = userConnectInterface.findByName(loginUserRequest.getName());

        if (!passwordEncoder.matches(loginUserRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException(ExceptionStatus.WRONG_PASSWORD.getMessage());
        }

        String token = jwtUtil.createToken(user.getId(), user.getUserRole());
        return JwtResponse.from(token);
    }

    @Override
    public JwtResponse loginAdmin(LoginAdminRequest loginAdminRequest) {
        AdminUser adminUser = adminConnectorInterface.findByAdminCode(loginAdminRequest.getAdminCode());
        if (!passwordEncoder.matches(loginAdminRequest.getPassword(), adminUser.getPassword())) {
            throw new IllegalArgumentException(ExceptionStatus.WRONG_PASSWORD.getMessage());
        }
        String token = jwtUtil.createToken(adminUser.getId(), adminUser.getUserRole());
        return JwtResponse.from(token);
    }

}
