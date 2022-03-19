package com.example.clubmanagementservice.global.exception;

import com.example.clubmanagementservice.domain.member.exception.MemberExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by ShinD on 2022/03/18.
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> baseExHandle(BaseException baseEx){
        return ResponseEntity
                .status(baseEx.getExceptionType().getHttpStatus())
                .body(ExceptionResponse.of(baseEx.getExceptionType().getErrorCode(),
                                           baseEx.getExceptionType().getErrorMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> authExHandle(UsernameNotFoundException usernameNotFoundEx){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ExceptionResponse.of(MemberExceptionType.NOT_FOUND.getErrorCode(),
                        MemberExceptionType.NOT_FOUND.getErrorMessage()));
    }
}
