package com.example.clubmanagementservice.domain.club.service;

import com.example.clubmanagementservice.domain.club.controller.dto.response.ClubInfoResponse;
import com.example.clubmanagementservice.domain.club.controller.dto.response.ClubListResponse;
import com.example.clubmanagementservice.domain.club.controller.dto.response.ClubMemberListResponse;

import java.util.ArrayList;

/**
 * Created by ShinD on 2022/03/20.
 */
public interface ClubService {
    /**
     * 가입신청
     * 가입수락
     * 가입거절
     * 회원목록조회
     * 동아리정보조회
     * 동아리리스트조회
     */

    void requestJoin(Long clubId, Long memberId);
    void acceptJoin(Long clubId, Long requesterId, Long accepterId);
    void rejectJoin(Long clubId, Long requesterId, Long disclaimerId);

    ClubMemberListResponse getMemberList();
    ClubListResponse getClubList();
    ClubInfoResponse getClubInfoById(Long clubId);

}
