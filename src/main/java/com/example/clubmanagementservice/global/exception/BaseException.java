package com.example.clubmanagementservice.global.exception;

/**
 * Created by ShinD on 2022/03/15.
 */
public abstract class BaseException extends RuntimeException{
    public abstract ExceptionType getExceptionType();
}
