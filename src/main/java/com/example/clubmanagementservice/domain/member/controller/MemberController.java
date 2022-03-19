package com.example.clubmanagementservice.domain.member.controller;

import com.example.clubmanagementservice.domain.member.controller.dto.request.SignUpRequest;
import com.example.clubmanagementservice.domain.member.exception.MemberException;
import com.example.clubmanagementservice.domain.member.service.MemberService;
import com.example.clubmanagementservice.global.aop.log.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by ShinD on 2022-03-15.
 */
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @Trace
    @PostMapping
    public ResponseEntity<Void> singUp(@RequestBody SignUpRequest signUpRequest) {
        memberService.signUp(signUpRequest.toServiceDto());
        return ResponseEntity.status(CREATED).build();
    }

}
