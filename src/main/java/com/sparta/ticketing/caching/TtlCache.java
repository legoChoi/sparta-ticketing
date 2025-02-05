package com.sparta.ticketing.caching;

import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TtlCache extends ConcurrentMapCache {
    private final Map<Object, Long> cacheTimestamps = new ConcurrentHashMap<>();
    private static final long TTL = 60 * 1000; // 60초 TTL

    public TtlCache(String name) {
        super(name);
    }


    @Override
    public void put(Object key, Object value) {
        super.put(key, value);
        cacheTimestamps.put(key, System.currentTimeMillis());
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
        Long timestamp = cacheTimestamps.get(key);
        return timestamp == null || (System.currentTimeMillis() - timestamp) > TTL;
    }

    @Override
    public void evict(Object key) {
        super.evict(key);
        cacheTimestamps.remove(key);
    }
}
