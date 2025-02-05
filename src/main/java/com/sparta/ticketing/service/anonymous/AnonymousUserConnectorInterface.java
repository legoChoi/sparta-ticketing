package com.sparta.ticketing.service.anonymous;

import com.sparta.ticketing.dto.anonymous.AnonymousUserRequest;
import com.sparta.ticketing.entity.AnonymousUser;
import org.springframework.stereotype.Component;

@Component
public interface AnonymousUserConnectorInterface {
    AnonymousUser addAnonymousUser(AnonymousUserRequest anonymousUserRequest);

    void deleteAnonymousUser(AnonymousUserRequest anonymousUserRequest);
}
