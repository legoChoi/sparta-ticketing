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
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        AtomicInteger successCnt = new AtomicInteger();
        AtomicInteger failCnt = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    reservationService.addReservation(1L, 1L, Thread.currentThread().getName());
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
        Assertions.assertThat(failCnt.get()).isEqualTo(threadCount - 1);
    }

    // 동시에 여러 좌석에 대한 예약을 요청할 때 각 좌석에 대해서만 lock이 잘 걸리는지 테스트
    @Test
    void multipleSeatReservationTest() throws InterruptedException {
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        AtomicInteger successCnt = new AtomicInteger();
        AtomicInteger failCnt = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            int iVal = i;
            executor.submit(() -> {
                try {
                    reservationService.addReservation(
                        iVal % 2L + 1L, iVal % 2L + 1L, Thread.currentThread().getName());
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

        Assertions.assertThat(successCnt.get()).isEqualTo(2);
        Assertions.assertThat(failCnt.get()).isEqualTo(threadCount - 2);
    }
}