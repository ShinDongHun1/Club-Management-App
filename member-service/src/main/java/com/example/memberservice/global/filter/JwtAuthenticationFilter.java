package com.example.memberservice.global.filter;

import com.example.memberservice.domain.member.entity.Member;
import com.example.memberservice.domain.member.repository.MemberRepository;
import com.example.memberservice.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShinD on 2022/03/15.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final List<String> noCheckUris = List.of("/h2-console");
    private final static String SIGNUP_URI = "/member";

    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(noCheckUris.contains(request.getRequestURI()) ||
            (SIGNUP_URI.equals(request.getRequestURI()) && HttpMethod.POST.matches(request.getMethod()))
        ){
            filterChain.doFilter(request,response);
            return;
        }

        Member member = jwtService.extractToken(request)
                .filter(jwtService::isValid)
                .map(jwtService::extractMemberId)
                .flatMap(memberRepository::findById)
                .orElse(null);

        if(member == null){
            filterChain.doFilter(request,response);
            return;
        }

        saveSecurityContextHolder(member);

    }



    private void saveSecurityContextHolder(Member member) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(User.builder()
                .username(member.getId().toString())
                .password(member.getPassword())//이거 안해주면 오류남!
                .authorities(new ArrayList<>()).build(), null, new ArrayList<>()));
        SecurityContextHolder.setContext(context);
    }
}
