//package com.sparta.ticketing;
//
//import com.sparta.ticketing.entity.Concert;
//import com.sparta.ticketing.entity.Hall;
//import com.sparta.ticketing.entity.Seat;
//import com.sparta.ticketing.entity.Session;
//import com.sparta.ticketing.repository.concert.ConcertRepository;
//import com.sparta.ticketing.repository.hall.HallRepository;
//import com.sparta.ticketing.repository.seat.SeatRepository;
//import com.sparta.ticketing.repository.session.SessionRepository;
//import com.sparta.ticketing.service.concert.ConcertConnectorInterface;
//import com.sparta.ticketing.service.concert.ConcertService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Commit;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@SpringBootTest
//@Transactional
//public class DummyCreatorTest {
//    @Autowired ConcertRepository concertRepository;
//    @Autowired ConcertService concertService;
//    @Autowired HallRepository hallRepository;
//    @Autowired SeatRepository seatsRepository;
//    @Autowired SessionRepository sessionRepository;
//
//    @Test
//    @Commit
//    void createDummy() {
//        Concert concert = new Concert("concert");
//        concertRepository.save(concert);
//
//        Hall hall = new Hall("hall", "seoul");
//        hallRepository.save(hall);
//
//        Session session = new Session(hall, concert, LocalDateTime.now(), LocalDateTime.now(),10);
//        sessionRepository.save(session);
//
//        for (int i = 1; i <= 10; i++) {
//            seatsRepository.save(new Seat(session, i));
//        }
//    }
//
//    @Test
//    @Commit
//    void createBulkConcerts() {
//        concertService.bulkInsert(50000);
//    }
//}
