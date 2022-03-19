package com.example.clubmanagementservice.global.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by ShinD on 2022/03/15.
 */
public interface JwtService {
    String createAccessToken(Long id);

    Long extractMemberId(String jwt);

    void sendToken(HttpServletResponse response, String jwt);

    Optional<String> extractToken(HttpServletRequest request);

    boolean isValid(String jwt);
}
