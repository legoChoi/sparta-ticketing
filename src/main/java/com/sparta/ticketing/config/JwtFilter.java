package com.sparta.ticketing.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InvalidClassException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    private final List<String> allowedPrefix = List.of("/concerts", "/sessions", "/reservations", "/seats");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String url = httpRequest.getRequestURI();

        if (allowedPrefix.stream().anyMatch(url::startsWith)) {
            chain.doFilter(request, response);
            return;
        }

        String bearerJwt = httpRequest.getHeader("Authorization");

        if (bearerJwt == null) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "JWT 토큰이 필요합니다.");
            return;
        }

        String jwt = jwtUtil.substringToken(bearerJwt);

        try {
            Claims claims = jwtUtil.extractClaims(jwt);
            String username = claims.getSubject();
            String role = claims.get("userRole", String.class);

            request.setAttribute("userId", username);
            request.setAttribute("userRole", role);

            chain.doFilter(request,response);


        } catch (SecurityException | MalformedJwtException e) {
        log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.", e);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않는 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
        log.error("Expired JWT token, 만료된 JWT token 입니다.", e);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
        log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.", e);
        httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "지원되지 않는 JWT 토큰입니다.");
    } catch (Exception e) {
        log.error("Internal server error", e);
        httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}