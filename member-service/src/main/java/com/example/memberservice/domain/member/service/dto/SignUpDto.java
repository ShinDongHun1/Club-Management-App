package com.example.memberservice.domain.member.service.dto;


import com.example.memberservice.domain.member.entity.Member;

/**
 * Created by ShinD on 2022-03-15.
 */
public record SignUpDto(String studentId, String password) {

    public Member toEntity() {
        return Member.builder().studentId(studentId).password(password).build();
    }
}
