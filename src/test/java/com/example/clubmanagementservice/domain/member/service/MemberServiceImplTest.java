package com.example.clubmanagementservice.domain.member.service;

import com.example.clubmanagementservice.domain.member.controller.dto.request.SignUpRequest;
import com.example.clubmanagementservice.domain.member.exception.MemberException;
import com.example.clubmanagementservice.domain.member.service.dto.MemberInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static com.example.clubmanagementservice.domain.member.exception.MemberExceptionType.ALREADY_EXIST;
import static com.example.clubmanagementservice.domain.member.exception.MemberExceptionType.NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ShinD on 2022/03/16.
 */
@SpringBootTest
@Transactional
class MemberServiceImplTest {


    @Autowired
    MemberService memberService;
    @Autowired
    EntityManager em;



    @Test
    @DisplayName("회원가입 성공")
    public void successSignUp() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("201902708", "1234");

        //when
        Long memberId = memberService.signUp(signUpRequest.toServiceDto());


        //then
        MemberInfoDto member = memberService.getInfo(memberId);
        assertThat(member.getId()).isEqualTo(memberId);
        assertThat(member.getStudentId()).isEqualTo(signUpRequest.getStudentId());
    }




    @Test
    @DisplayName("회원가입 실패 - 학번이 중복")
    public void failSignUpCauseDuplicateStudentId() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("201902708", "1234");
        Long memberId = memberService.signUp(signUpRequest.toServiceDto());
        MemberInfoDto member = memberService.getInfo(memberId);


        //when, then
        assertThat(
                assertThrows(MemberException.class, () -> memberService.signUp(signUpRequest.toServiceDto())).getExceptionType()
        ).isEqualTo(ALREADY_EXIST);

        assertThat(em.createQuery("select m from Member m").getResultList().size()).isEqualTo(1);
    }




    @Test
    @DisplayName("회원정보 조회 성공")
    public void successGetInfo() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("201902708", "1234");
        Long memberId = memberService.signUp(signUpRequest.toServiceDto());

        //when
        MemberInfoDto member = memberService.getInfo(memberId);

        //then
        assertThat(member.getId()).isEqualTo(memberId);
        assertThat(member.getStudentId()).isEqualTo(signUpRequest.getStudentId());
    }


    @Test
    @DisplayName("회원정보 조회 실패 - 없는 회원 조회")
    public void failGetInfo() throws Exception {
        //given
        Long noExistMemberId = 1L;

        //when, then
        assertThat(
                assertThrows(MemberException.class, () -> memberService.getInfo(noExistMemberId)).getExceptionType()
        ).isEqualTo(NOT_FOUND);

    }
}