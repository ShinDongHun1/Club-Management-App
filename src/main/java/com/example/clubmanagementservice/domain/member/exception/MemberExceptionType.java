package com.example.clubmanagementservice.domain.member.exception;

import com.example.clubmanagementservice.global.exception.ExceptionType;
import org.springframework.http.HttpStatus;

/**
 * Created by ShinD on 2022/03/15.
 */
public enum MemberExceptionType implements ExceptionType {

    ALREADY_EXIST(101, HttpStatus.CONFLICT, "이미 존재하는 학번입니다."),
    NOT_FOUND(104, HttpStatus.NOT_FOUND, "찾으시는 회원이 존재하지 않습니다.");


    private int errorCode;
    private HttpStatus httpStatus;
    private String message;

    MemberExceptionType(int errorCode, HttpStatus httpStatus, String message) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
