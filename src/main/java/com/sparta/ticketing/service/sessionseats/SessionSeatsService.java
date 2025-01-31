package com.sparta.ticketing.service.sessionseats;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionSeatsService {
    private final SessionSeatsConnectorInterface sessionSeatsConnectorInterface;


}
