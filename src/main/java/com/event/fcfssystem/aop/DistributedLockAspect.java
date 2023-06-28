package com.event.fcfssystem.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(value = 1)
public class DistributedLockAspect {

    private static final String PREFIX = "lock";
    private static final String SEPARATOR = "_";
    private final RedissonClient redissonClient;

    public DistributedLockAspect(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Pointcut("@annotation(com.event.fcfssystem.aop.DistributedLock)")
    private void distributedLock() {
    }

    @Around(value = "distributedLock()")
    public Object lock(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);

        RLock lock = redissonClient.getLock(createLockName(signature.getParameterNames(), pjp.getArgs(), distributedLock));

        try {
            boolean available = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());
            if (!available) {
                return false;
            }

            return pjp.proceed();
        } catch (InterruptedException e) {
            throw new InterruptedException();
        } finally {
            lock.unlock();
        }
    }

    private String createLockName(String[] parameterNames, Object[] args, DistributedLock distributedLock) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        Object value = parser.parseExpression(distributedLock.value()).getValue(context, Object.class);

        return PREFIX + SEPARATOR + distributedLock.value() + SEPARATOR + value;
    }
}
