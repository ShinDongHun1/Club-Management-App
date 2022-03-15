package com.example.memberservice.global.filter;

import com.example.memberservice.domain.member.service.MemberService;
import com.example.memberservice.global.jwt.JwtService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by ShinD on 2022/03/15.
 */
public class StudentIdPasswordJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_FORM_STUDENT_ID_KEY = "studentId";



    private final MemberService memberService;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    public StudentIdPasswordJsonAuthenticationFilter(AuthenticationManager authenticationManager,
                                                     MemberService memberService,
                                                     JwtService jwtService,
                                                     ObjectMapper objectMapper) {
        super(authenticationManager);
        this.memberService = memberService;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }



    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }


        Map<String, String> authMap = objectMapper.readValue(getMessageBody(request), new TypeReference<Map<String, String>>() {});


        String username = authMap.get(SPRING_SECURITY_FORM_STUDENT_ID_KEY);
        username = (username != null) ? username.trim() : "";

        String password =  authMap.get(SPRING_SECURITY_FORM_PASSWORD_KEY); //UsernamePasswordAuthenticationFilter 에서 정의되어있음
        password = (password != null) ? password : "";

        return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private String getMessageBody(HttpServletRequest request) throws IOException {
        return StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
    }




    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        jwtService.sendToken(response ,
                jwtService.createAccessToken(
                        //회원의 Id(식별값)을 가지고 JWT 발급
                        memberService.getInfoByStudentId(
                                ((User) authResult.getPrincipal()).getUsername()
                        ).getId()
                ));
    }
}
