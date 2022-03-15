package com.example.memberservice.domain.member.exception;

import com.example.memberservice.global.exception.ExceptionType;
import org.springframework.http.HttpStatus;

/**
 * Created by ShinD on 2022/03/15.
 */
public enum MemberExceptionType implements ExceptionType {

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
