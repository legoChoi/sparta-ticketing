package com.sparta.ticketing.service.reservation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired ReservationService reservationService;

    @Test
    void success1() {
        reservationService.addReservation(1L, 1L, "tt");


    }

    @Test
    void test() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(58);
        CountDownLatch latch = new CountDownLatch(1000);

        AtomicInteger successCnt = new AtomicInteger();
        AtomicInteger failCnt = new AtomicInteger();

        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    reservationService.addReservation(1L, 1L, "user");
                    successCnt.incrementAndGet();
                } catch (Exception e) {
                    failCnt.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        System.out.println("successCnt = " + successCnt);
        System.out.println("failCnt = " + failCnt);

        Assertions.assertThat(successCnt.get()).isEqualTo(1);
        Assertions.assertThat(failCnt.get()).isEqualTo(999);
    }
}