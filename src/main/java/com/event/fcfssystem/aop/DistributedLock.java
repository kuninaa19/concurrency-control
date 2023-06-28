package com.event.fcfssystem.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedLock {

    /* The name of the resource to lock */
    String value();

    /* wait for lock aquisition */
    long waitTime() default 3L;

    /* automatically unlock */
    long leaseTime() default 2L;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}
