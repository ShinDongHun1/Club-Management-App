package com.example.clubmanagementservice.domain.member.service;


import com.example.clubmanagementservice.domain.member.service.dto.MemberInfoDto;
import com.example.clubmanagementservice.domain.member.service.dto.SignUpDto;
import com.example.clubmanagementservice.global.aop.log.Trace;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by ShinD on 2022-03-15.
 */
public interface MemberService extends UserDetailsService {


    @Trace
    Long signUp(SignUpDto signUpDto);

    @Trace
    MemberInfoDto getInfo(Long memberId);

    @Trace
    MemberInfoDto getInfoByStudentId(String studentId);
}
