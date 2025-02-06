package com.sparta.ticketing.aop;

import com.sparta.ticketing.aop.annotation.CacheEvictPattern;
import com.sparta.ticketing.caching.CustomCache;
import com.sparta.ticketing.config.SpelUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CacheEvictByPatternAspect {
    private final CacheManager cacheManager;
    private final SpelUtil spelUtil;

    @Before(value = "@annotation(evictByPattern)", argNames = "evictByPattern,joinPoint")
    public void evictCacheByPattern(CacheEvictPattern evictByPattern, JoinPoint joinPoint) {
        String evaluatedPattern = spelUtil.evaluate(joinPoint, evictByPattern.pattern());

        CustomCache cache = (CustomCache) cacheManager.getCache(evictByPattern.value());

        if (cache != null) {
            cache.evictByPattern(evaluatedPattern);
        }
    }
}
