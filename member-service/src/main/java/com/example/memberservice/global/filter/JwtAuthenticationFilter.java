package com.example.memberservice.global.filter;

import com.example.memberservice.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ShinD on 2022/03/15.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final List<String> noCheckUrls = List.of("/login");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(noCheckUrls.contains(request.getRequestURI())){
            filterChain.doFilter(request,response);
            return;
        }

        jwtService.extractToken(request).filter(jwtService::isValid).ifPresent(
                token -> jwtService.extractMemberId(token).ifPresent(this::saveSecurityContextHolder)
        );

    }

    private void saveSecurityContextHolder(Long id) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UsernamePasswordAuthenticationToken(id, null, null));
        SecurityContextHolder.setContext(context);
    }
}
