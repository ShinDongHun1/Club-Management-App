package com.example.memberservice.domain.member.controller.dto.request;

import com.example.memberservice.domain.member.service.dto.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by ShinD on 2022-03-15.
 */

@AllArgsConstructor
@Getter
public class SignUpRequest {

    private String studentId;
    private String password;

    public SignUpDto toServiceDto() {
        return new SignUpDto(studentId, password);
    }
}
