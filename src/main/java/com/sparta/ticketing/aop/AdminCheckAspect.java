package com.sparta.ticketing.aop;

import com.sparta.ticketing.config.JwtUtil;
import com.sparta.ticketing.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminCheckAspect {

    private final HttpServletRequest httpServletRequest;

    @Before("@annotation(com.sparta.ticketing.aop.OnlyAdmin)")
    public void checkAdminRole() {
        String userRole = (String) httpServletRequest.getAttribute("userRole");
        if (!"ROLE_ADMIN".equals(userRole)) {
            throw new IllegalStateException("관리자 권한필요");
        }
        log.info("관리자 권한 확인완료");
    }

}
