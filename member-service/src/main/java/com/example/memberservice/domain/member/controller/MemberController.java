package com.example.memberservice.domain.member.controller;

import com.example.memberservice.domain.member.controller.dto.request.SignUpRequest;
import com.example.memberservice.domain.member.service.MemberService;
import com.example.memberservice.global.aop.log.Trace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
    public ResponseEntity<Void> singUp(@RequestBody SignUpRequest signUpRequest){
        memberService.signUp(signUpRequest.toServiceDto());
        return ResponseEntity.status(CREATED).build();
    }
}
