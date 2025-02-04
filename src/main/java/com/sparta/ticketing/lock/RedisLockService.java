package com.sparta.ticketing.lock;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisLockService {
    private final RedisTemplate<String, String> redisTemplate;

    // 각 서비스 메서드마다 락 유효 시간을 애노테이션으로 설정할 수 있도록 필드 제거

    // 락을 획득하는 메서드
    // 각 회차 및 좌석 별로 별도의 lock이 적용되도록 생성된 key를 전달받음
    public boolean acquireLock(String lockKey, String lockValue, long timeout) {
        Boolean isLocked = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, timeout, TimeUnit.MILLISECONDS);
        return Boolean.TRUE.equals(isLocked);
    }

    // 락을 해제하는 메서드
    public void releaseLock(String lockKey, String lockValue) {
        String currentLockValue = redisTemplate.opsForValue().get(lockKey);
        // 락을 획득한 값과 일치하면 락을 해제
        if (lockValue.equals(currentLockValue)) {
            redisTemplate.delete(lockKey);
        }
    }

}
