package com.example.memberservice.domain.member.service;

import com.example.memberservice.domain.member.service.dto.SignUpDto;
import com.example.memberservice.global.aop.log.Trace;

/**
 * Created by ShinD on 2022-03-15.
 */
public interface MemberService {


    @Trace
    Long signUp(SignUpDto signUpDto);

}
