package com.example.clubmanagementservice.domain.clubmember.entity;

/**
 * Created by ShinD on 2022/03/20.
 */
import com.example.clubmanagementservice.domain.club.entity.Club;
import com.example.clubmanagementservice.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ClubMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_member_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id")
    private Club club;


    private Role role;//PRESIDENT, EXECUTIVES, BASIC
}
