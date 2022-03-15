package com.example.memberservice.global.aop.log;

/**
 * Created by ShinD on 2022-03-15.
 */
public interface LogTrace {
    TraceStatus begin(String message, String id);

    void end(TraceStatus status);
    void exception(TraceStatus status, Throwable e);
}
