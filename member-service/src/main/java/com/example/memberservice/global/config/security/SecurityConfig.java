package com.example.memberservice.global.config.security;

import com.example.memberservice.domain.member.repository.MemberRepository;
import com.example.memberservice.domain.member.service.MemberService;
import com.example.memberservice.global.filter.JwtAuthenticationFilter;
import com.example.memberservice.global.filter.StudentIdPasswordJsonAuthenticationFilter;
import com.example.memberservice.global.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by ShinD on 2022/03/15.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()

                .authorizeRequests()
                .antMatchers("/login", "/h2-console").permitAll()
                .antMatchers(HttpMethod.POST, "/member").permitAll()// /member 로 들어오는 POST 요청은 회원가입이므로 허락
                .anyRequest().authenticated()

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//해주지 않으면 세션을 공유하기 때문에 로그인 정보가 계속 공유되어버린다(SecurityContextHolder 에 들어있는 정보가 계속해서 공유)

                .headers().frameOptions().sameOrigin()

                .and()
                .addFilter(studentIdPasswordJsonAuthenticationFilter())
                .addFilterBefore(jwtAuthenticationFilter(), StudentIdPasswordJsonAuthenticationFilter.class);//WebAsyncManagerIntegrationFilter 앞에 두어도 되지만 일단 이곳에 위치시켰다
    }


    private StudentIdPasswordJsonAuthenticationFilter studentIdPasswordJsonAuthenticationFilter() throws Exception {
        return new StudentIdPasswordJsonAuthenticationFilter(
                authenticationManager(),
                memberService,
                jwtService,
                objectMapper
        );
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtService, memberRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);
    }
}
