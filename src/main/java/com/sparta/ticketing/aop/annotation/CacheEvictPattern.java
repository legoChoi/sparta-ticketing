package com.sparta.ticketing.aop.annotation;

import org.springframework.cache.annotation.CacheEvict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@CacheEvict(value = "concertCache", allEntries = false)
public @interface CacheEvictPattern {
    String pattern();
    String value();
}
