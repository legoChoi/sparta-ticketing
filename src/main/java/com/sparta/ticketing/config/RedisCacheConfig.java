//package com.sparta.ticketing.config;
//
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.cache.RedisCacheManager;
//
//import java.time.Duration;
//
//
//@Configuration
//@EnableCaching
//public class RedisCacheConfig {
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        // TTL 60초 설정
//        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//            .entryTtl(Duration.ofSeconds(60))
//            .disableCachingNullValues();
//
//        return RedisCacheManager.builder(redisConnectionFactory)
//            .cacheDefaults(cacheConfig)
//            .build();
//    }
//}