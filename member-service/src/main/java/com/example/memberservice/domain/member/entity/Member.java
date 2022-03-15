package com.example.memberservice.domain.member.entity;

/**
 * Created by ShinD on 2022/03/15.
 */
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String studentId;
    private String password;



    @Builder
    public Member(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    public void encodePassword(PasswordEncoder encoder) {

    }
}
