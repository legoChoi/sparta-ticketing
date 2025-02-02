package com.sparta.ticketing.repository.session;

import com.sparta.ticketing.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
