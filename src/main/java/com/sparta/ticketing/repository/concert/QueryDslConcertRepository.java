package com.sparta.ticketing.repository.concert;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.ticketing.dto.concert.GetConcertResponse;
import com.sparta.ticketing.entity.QConcert;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QueryDslConcertRepository {
    private final JPAQueryFactory queryFactory;

    public QueryDslConcertRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<GetConcertResponse> searchConcert(String name, Pageable pageable) {
        QConcert concert = QConcert.concert;

        JPAQuery<GetConcertResponse> query = queryFactory
            .select(Projections.constructor(GetConcertResponse.class, concert.id, concert.name))
            .from(concert)
            .where(concert.name.like("%" + name + "%"));

        if (pageable != null) {
            query.offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        }

        return query.fetch();
    }
}
