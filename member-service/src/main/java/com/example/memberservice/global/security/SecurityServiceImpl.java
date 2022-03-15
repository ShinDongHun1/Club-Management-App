package com.example.memberservice.global.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by ShinD on 2022-03-15.
 */
@Component
public class SecurityServiceImpl implements SecurityService {
    @Override
    public Long getLoginUserId() {
        try {
            return Long.parseLong(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        }catch (NullPointerException | ClassCastException e) {
            /*
             ClassCastException : getPrincipal이 anonymousUser일때 발생 (String 을 UserDetails로 바꿀 수 없다는 메세지)
             */
            return null;
        }
    }
}
