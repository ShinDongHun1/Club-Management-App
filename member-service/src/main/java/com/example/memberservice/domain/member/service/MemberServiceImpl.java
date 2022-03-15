package com.example.memberservice.domain.member.service;

import com.example.memberservice.domain.member.entity.Member;
import com.example.memberservice.domain.member.exception.MemberException;
import com.example.memberservice.domain.member.exception.MemberExceptionType;
import com.example.memberservice.domain.member.repository.MemberRepository;
import com.example.memberservice.domain.member.service.dto.MemberInfoDto;
import com.example.memberservice.domain.member.service.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.memberservice.domain.member.exception.MemberExceptionType.NOT_FOUND;


/**
 * Created by ShinD on 2022-03-15.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final PasswordEncoder encoder;
    private final MemberRepository memberRepository;



    @Override
    public Long signUp(SignUpDto signUpDto) {


        checkDuplicateStudentId(signUpDto.studentId());

        Member member = signUpDto.toEntity();
        member.encodePassword(encoder);

        // member.getId(); 는 현재 null이므로 save()를 톨해 반환된 member의 id를 전달
        return memberRepository.save(member).getId();
    }


    private String checkDuplicateStudentId(String studentId) {
        if(memberRepository.findByStudentId(studentId).isPresent()) throw new MemberException(NOT_FOUND);
        return studentId;
    }


    @Override
    public MemberInfoDto getInfo(Long memberId) {
        return new MemberInfoDto(
                memberRepository.findById(memberId).orElseThrow(() -> new MemberException(NOT_FOUND))
        );
    }
}
