//package com.sparta.ticketing.lock;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import java.util.concurrent.TimeUnit;
//
//@Service
//@RequiredArgsConstructor
//public class RedisLockService {
//    private final RedisTemplate<String, String> redisTemplate;
//
//    private static final String LOCK_KEY = "myLock";  // 락을 위한 키
//    private static final long LOCK_TIMEOUT = 10000L;  // 락 유효 시간 (밀리초)
//
//    // 락을 획득하는 메서드
//    public boolean acquireLock(String lockValue) {
//        Boolean isLocked = redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, lockValue, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
//        return Boolean.TRUE.equals(isLocked);
//    }
//
//    // 락을 해제하는 메서드
//    public void releaseLock(String lockValue) {
//        String currentLockValue = redisTemplate.opsForValue().get(LOCK_KEY);
//        // 락을 획득한 값과 일치하면 락을 해제
//        if (lockValue.equals(currentLockValue)) {
//            redisTemplate.delete(LOCK_KEY);
//        }
//    }
//
//
//}
