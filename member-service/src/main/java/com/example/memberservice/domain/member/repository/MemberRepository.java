package com.example.memberservice.domain.member.repository;

import com.example.memberservice.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by ShinD on 2022/03/15.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByStudentId(String studentId);
}
