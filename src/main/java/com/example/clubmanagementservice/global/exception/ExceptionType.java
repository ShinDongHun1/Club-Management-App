package com.example.clubmanagementservice.global.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by ShinD on 2022/03/15.
 */
public interface ExceptionType {

    int getErrorCode();

    HttpStatus getHttpStatus();

    String getErrorMessage();
}
