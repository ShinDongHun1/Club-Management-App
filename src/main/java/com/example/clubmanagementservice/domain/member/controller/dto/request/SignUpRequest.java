package com.example.clubmanagementservice.domain.member.controller.dto.request;

import com.example.clubmanagementservice.domain.member.service.dto.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by ShinD on 2022-03-15.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpRequest {

    private String studentId;
    private String password;

    public SignUpDto toServiceDto() {
        return new SignUpDto(studentId, password);
    }
}
