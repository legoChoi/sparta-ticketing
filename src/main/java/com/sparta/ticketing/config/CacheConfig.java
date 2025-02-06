package com.sparta.ticketing.config;

import com.sparta.ticketing.caching.CustomCache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new CustomCacheManager();
    }

    static class CustomCacheManager extends ConcurrentMapCacheManager {
        @Override
        protected org.springframework.cache.Cache createConcurrentMapCache(String name) {
            // 캐시 이름을 사용하여 TTLCache 생성
            return new CustomCache(name);
        }
    }
}