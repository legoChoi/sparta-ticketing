package com.sparta.ticketing.repository.session;

import com.sparta.ticketing.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("select s from Session s join fetch s.hall join fetch s.concert where s.id = :sessionId")
    public Optional<Session> findFirstById(@Param("sessionId") Long sessionId);
}
