package com.sparta.ticketing.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {
    int maxRetry() default 3;
    long retryDelay() default 100L;
    long timeout() default 10000L;
    String key();
}
