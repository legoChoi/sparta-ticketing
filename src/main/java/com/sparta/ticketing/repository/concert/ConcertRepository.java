package com.sparta.ticketing.repository.concert;

import com.sparta.ticketing.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
