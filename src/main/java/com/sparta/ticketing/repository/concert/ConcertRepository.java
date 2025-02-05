package com.sparta.ticketing.repository.concert;

import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.entity.Concert;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConcertRepository extends JpaRepository<Concert, Long> {
    @Query("select new com.sparta.ticketing.dto.concert.GetConcertResponse(c.id, c.name) " +
        "from Concert c where c.name like concat('%', :name, '%') ")
    public List<GetConcertResponse> searchConcert(String name, Pageable pageable);
}
