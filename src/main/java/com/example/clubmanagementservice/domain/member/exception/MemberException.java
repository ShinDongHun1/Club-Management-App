package com.example.clubmanagementservice.domain.member.exception;


import com.example.clubmanagementservice.global.exception.BaseException;
import com.example.clubmanagementservice.global.exception.ExceptionType;

/**
 * Created by ShinD on 2022/03/15.
 */
public class MemberException extends BaseException {

    private MemberExceptionType memberExceptionType;

    public MemberException(MemberExceptionType memberExceptionType) {
        this.memberExceptionType = memberExceptionType;
    }

    @Override
    public ExceptionType getExceptionType() {
        return this.memberExceptionType;
    }
}
