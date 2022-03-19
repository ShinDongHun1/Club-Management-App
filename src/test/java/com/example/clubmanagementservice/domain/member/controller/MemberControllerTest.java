package com.example.clubmanagementservice.domain.member.controller;

import com.example.clubmanagementservice.domain.member.controller.dto.request.SignUpRequest;
import com.example.clubmanagementservice.domain.member.entity.Member;
import com.example.clubmanagementservice.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ShinD on 2022-03-19.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager em;
    @Autowired
    private MemberRepository memberRepository;



    private ObjectMapper objectMapper = new ObjectMapper();



    @Test
    @DisplayName("회원가입 성공")
    public void successSignUp() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("201902708","1234");


        //when
        mockMvc.perform(
                post("/member")
                        .content(objectMapper.writeValueAsString(signUpRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //then
        Member member = memberRepository.findByStudentId("201902708").get();
        Assertions.assertThat(member).isNotNull();

    }


    @Test
    @DisplayName("회원가입 실패 - 학번이 겹침")
    public void failSignUp() throws Exception {
        //given
        SignUpRequest signUpRequest = new SignUpRequest("201902708", "1234");

        memberRepository.save(signUpRequest.toServiceDto().toEntity());

        //when, then
        mockMvc.perform(
                        post("/member")
                                .content(objectMapper.writeValueAsString(signUpRequest))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());


    }
}