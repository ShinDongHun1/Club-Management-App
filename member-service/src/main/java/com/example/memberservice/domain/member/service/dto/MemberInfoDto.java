package com.example.memberservice.domain.member.service.dto;

import com.example.memberservice.domain.member.entity.Member;
import lombok.Getter;

/**
 * Created by ShinD on 2022/03/15.
 */
@Getter
public class MemberInfoDto {

    private Long id;
    private String studentId;

    public MemberInfoDto(Member member) {
        this.id = member.getId();
        this.studentId = member.getStudentId();
    }

}
