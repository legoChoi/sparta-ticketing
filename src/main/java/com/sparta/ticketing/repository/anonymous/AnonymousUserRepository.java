package com.sparta.ticketing.repository.anonymous;

import com.sparta.ticketing.entity.AnonymousUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnonymousUserRepository extends JpaRepository<AnonymousUser, Long> {

    AnonymousUser findByNickname(String nickname);
}
