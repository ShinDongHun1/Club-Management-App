package com.example.memberservice.domain.member.service;

import com.example.memberservice.domain.member.controller.dto.request.SignUpRequest;
import com.example.memberservice.domain.member.exception.MemberException;
import com.example.memberservice.domain.member.service.dto.MemberInfoDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static com.example.memberservice.domain.member.exception.MemberExceptionType.NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by ShinD on 2022/03/15.
 */
@SpringBootTest
@Transactional
class MemberServiceImplTest {


    @Autowired MemberService memberService;
    @Autowired EntityManager em;


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
        ).isEqualTo(NOT_FOUND);

        assertThat(em.createQuery("select m from Member m").getResultList().size()).isEqualTo(1);
    }


}