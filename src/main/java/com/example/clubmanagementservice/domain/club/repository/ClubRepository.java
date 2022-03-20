package com.example.clubmanagementservice.domain.club.repository;

import com.example.clubmanagementservice.domain.club.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ShinD on 2022/03/20.
 */
public interface ClubRepository extends JpaRepository<Club, List> {
}
