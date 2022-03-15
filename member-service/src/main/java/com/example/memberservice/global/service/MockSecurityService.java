package com.example.memberservice.global.service;

import org.springframework.stereotype.Component;

/**
 * Created by ShinD on 2022-03-15.
 */
@Component
public class MockSecurityService implements SecurityService {
    @Override
    public String getLoginUsername() {
        return null;
    }
}
