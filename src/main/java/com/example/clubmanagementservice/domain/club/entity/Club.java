package com.example.clubmanagementservice.domain.club.entity;

/**
 * Created by ShinD on 2022/03/20.
 */
import com.example.clubmanagementservice.domain.clubmember.entity.ClubMember;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Club {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long id;

    private String name;

    private String info;

    private int numOfMember;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ClubMember> clubMembers = new ArrayList<>();


}
