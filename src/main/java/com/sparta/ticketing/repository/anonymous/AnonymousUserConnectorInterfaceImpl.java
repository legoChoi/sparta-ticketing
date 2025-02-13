package com.sparta.ticketing.repository.anonymous;

import com.sparta.ticketing.config.PasswordEncoder;
import com.sparta.ticketing.dto.anonymous.AnonymousUserRequest;
import com.sparta.ticketing.entity.AnonymousUser;
import com.sparta.ticketing.exception.ExceptionStatus;
import com.sparta.ticketing.service.anonymous.AnonymousUserConnectorInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AnonymousUserConnectorInterfaceImpl implements AnonymousUserConnectorInterface {

    private final AnonymousUserRepository anonymousUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AnonymousUser addAnonymousUser(AnonymousUserRequest anonymousUserRequest) {
        String encodePassword = passwordEncoder.encode(anonymousUserRequest.getPassword());
        AnonymousUser anonymousUser = AnonymousUser.from(anonymousUserRequest.getNickname(), encodePassword);
        return anonymousUserRepository.save(anonymousUser);
    }

    @Override
    @Transactional
    public void deleteAnonymousUser(AnonymousUserRequest anonymousUserRequest) {
        AnonymousUser anonymousUser = anonymousUserRepository.findByNickname(anonymousUserRequest.getNickname());
        if (!passwordEncoder.matches(anonymousUserRequest.getPassword(), anonymousUser.getPassword())) {
            throw new IllegalStateException(ExceptionStatus.WRONG_PASSWORD.getMessage());
        }
        anonymousUserRepository.delete(anonymousUser);

    }
}
