package com.example.memberservice.domain.member.service;

import com.example.memberservice.domain.member.service.dto.MemberInfoDto;
import com.example.memberservice.domain.member.service.dto.SignUpDto;
import com.example.memberservice.global.aop.log.Trace;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by ShinD on 2022-03-15.
 */
public interface MemberService extends UserDetailsService {


    @Trace
    Long signUp(SignUpDto signUpDto);

    MemberInfoDto getInfo(Long memberId);

    MemberInfoDto getInfoByStudentId(String studentId);
}
