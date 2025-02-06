package com.sparta.ticketing.aop;

import com.sparta.ticketing.aop.annotation.RedisLock;
import com.sparta.ticketing.aop.annotation.RedisLock;
import com.sparta.ticketing.config.SpelUtil;
import com.sparta.ticketing.lock.RedisLockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;
import java.util.UUID;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RedisLockAspect {

    private final RedisLockService redisLockService;
    private final SpelUtil spelUtil;

    @Around("@annotation(com.sparta.ticketing.aop.annotation.RedisLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 서비스 로직 수행 전 lock 획득
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        RedisLock redisLockAnnotation = method.getAnnotation(RedisLock.class);

        int maxRetry = redisLockAnnotation.maxRetry();
        long retryDelay = redisLockAnnotation.retryDelay();
        long timeout = redisLockAnnotation.timeout();
        String lockKey = spelUtil.evaluate(joinPoint, redisLockAnnotation.key());
        String lockValue = UUID.randomUUID().toString();

        int retryCount = 0;

        boolean lockAcquired = redisLockService.acquireLock(lockKey, lockValue, timeout);

        // 최대 대기 시간이 설정된 Spin Lock
        while (!lockAcquired && retryCount < maxRetry) {
            ++retryCount;
            log.debug("repeat count: {}", retryCount);

            try {
                Thread.sleep(retryDelay);
            } catch (InterruptedException e) {
                log.info("Thread Interrupted");
            }
            lockAcquired = redisLockService.acquireLock(lockKey, lockValue, timeout);
        }
        if (!lockAcquired) {
            throw new RuntimeException("락 획득에 실패했습니다.");
        }

        // 서비스 로직 수행
        if(TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCompletion(int status) {
                    redisLockService.releaseLock(lockKey, lockValue);
                }
                // 필요에 따라 다른 콜백 메서드들도 구현 가능
            });
        }
        return joinPoint.proceed();
    }
}