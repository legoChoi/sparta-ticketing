package com.sparta.ticketing.caching;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class CustomCache extends ConcurrentMapCache {
    private final Map<Object, Long> mapCache = new ConcurrentHashMap<>();
    @Value("${spring.cache.redis.time-to-live}") // 60초 TTL
    private long TTL;

    public CustomCache(String name) {
        super(name);
    }

    @Override
    public void put(Object key, Object value) {
        super.put(key, value);
        mapCache.put(key, System.currentTimeMillis());
    }

    @Override
    public ValueWrapper get(Object key) {
        if (isExpired(key)) {
            evict(key);
            return null;
        }
        return super.get(key);
    }

    private boolean isExpired(Object key) {
        Long timestamp = mapCache.get(key);
        return timestamp == null || (System.currentTimeMillis() - timestamp) > TTL;
    }

    // 매개변수로 들어온 키 값의 캐시를 제거
    @Override
    public void evict(Object key) {
        super.evict(key);
        mapCache.remove(key);
    }

    // 정규식 기반으로 제거할 캐시의 키 값을 탐색
    public void evictByPattern(String pattern) {
        Pattern regex = Pattern.compile(pattern);
        mapCache.entrySet().removeIf(entry -> regex.matcher(entry.getKey().toString()).matches());
    }
}
