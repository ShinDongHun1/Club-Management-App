package com.example.clubmanagementservice.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by ShinD on 2022/03/18.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExceptionResponse {

    private int errorCode;
    private String message;

    public static ExceptionResponse of(int errorCode, String errorMessage) {
        return new ExceptionResponse(errorCode, errorMessage);
    }
}
