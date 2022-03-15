package com.example.memberservice.global.aop.log.aspect;

import com.example.memberservice.global.aop.log.LogTrace;
import com.example.memberservice.global.aop.log.TraceStatus;
import com.example.memberservice.global.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by ShinD on 2022-03-15.
 */
@Aspect
@Component
@Slf4j
public record LogAop(LogTrace logTrace,
                     SecurityService securityService) {

    @Around("@annotation(com.example.memberservice.global.aop.log.Trace)")//경로를 잘못 적어주면 java.lang.IllegalArgumentException: error Type referred to is not an annotation type
    public Object doLogTrace(ProceedingJoinPoint joinPoint) throws Throwable {

        TraceStatus status = null;

        try {
            status = logTrace.begin(joinPoint.getSignature().toShortString(), securityService.getLoginUsername());
            Object result = joinPoint.proceed();

            logTrace.end(status);

            return result;
        } catch (Throwable e) {
            e.printStackTrace();
            logTrace.exception(status, e);
            throw e;
        }
    }
}
