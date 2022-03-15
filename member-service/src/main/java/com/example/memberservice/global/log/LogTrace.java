package com.example.memberservice.global.log;

import com.example.memberservice.global.log.TraceStatus;

/**
 * Created by ShinD on 2022-03-15.
 */
public interface LogTrace {
    TraceStatus begin(String message, String id);

    void end(TraceStatus status);
    void exception(TraceStatus status, Throwable e);
}
