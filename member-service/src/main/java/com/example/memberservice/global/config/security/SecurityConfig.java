package com.example.memberservice.global.config.security;

import com.example.memberservice.domain.member.service.MemberService;
import com.example.memberservice.global.filter.StudentIdPasswordJsonAuthenticationFilter;
import com.example.memberservice.global.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ShinD on 2022/03/15.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()

                .headers().frameOptions().sameOrigin()

                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.POST, "/member").permitAll()// /member 로 들어오는 POST 요청은 회원가입이므로 허락
                .anyRequest().authenticated()

                .and()
                .addFilter(studentIdPasswordJsonAuthenticationFilter());
    }


    private StudentIdPasswordJsonAuthenticationFilter studentIdPasswordJsonAuthenticationFilter() throws Exception {
        return new StudentIdPasswordJsonAuthenticationFilter(
                authenticationManager(),
                memberService,
                jwtService,
                objectMapper
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);
    }
}
