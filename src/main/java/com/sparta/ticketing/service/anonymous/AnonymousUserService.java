package com.sparta.ticketing.service.anonymous;

import com.sparta.ticketing.dto.anonymous.AnonymousUserRequest;
import com.sparta.ticketing.dto.anonymous.AnonymousUserResponse;
import com.sparta.ticketing.entity.AnonymousUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnonymousUserService {
    private final AnonymousUserConnectorInterface anonymousUserConnectorInterface;

    public AnonymousUserResponse addAnonymousUser(AnonymousUserRequest anonymousUserRequest){
        AnonymousUser anonymousUserResponse = anonymousUserConnectorInterface.addAnonymousUser(anonymousUserRequest);

        return AnonymousUserResponse.from(anonymousUserResponse);
    }

    public void deleteAnonymousUser(AnonymousUserRequest anonymousUserRequest) {
        anonymousUserConnectorInterface.deleteAnonymousUser(anonymousUserRequest);
    }
}
