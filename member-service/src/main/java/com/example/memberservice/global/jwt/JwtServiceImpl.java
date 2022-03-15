package com.example.memberservice.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.memberservice.global.aop.log.Trace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by ShinD on 2022/03/15.
 */
@Service
public class JwtServiceImpl implements JwtService {

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String USER_ID_CLAIM = "userId";
    private static final String ACCESS_TOKEN_HEADER = "AccessToken";



    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationDay}")
    private long accessTokenValidity;//AccessToken 의 유효기간

    private Algorithm algorithm;


    @PostConstruct
    private void setAlgorithm(){
        algorithm = Algorithm.HMAC512(secretKey);
    }


    @Trace
    @Override
    public String createAccessToken(Long id) {
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)  //토큰의 제목
                .withClaim(USER_ID_CLAIM, id)       //토큰의 클레임(정보들)
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(accessTokenValidity, TimeUnit.DAYS)))
                .sign(algorithm);
        /*
            HMAC512 :
            해시 함수와, 키(key)를 가지고 해쉬값을 만들어 암호화시킨다.
            내가 찾아본 자료에 따르면 SHA-512는 64비트 컴퓨터에서 SHA-256보다 빠르다.(내부적으로 64비트 산술을 사용)
         */
    }



    @Override
    public Long extractMemberId(String jwt) {
        return JWT.require(algorithm).build().verify(jwt).getClaim(USER_ID_CLAIM).asLong();
    }


    @Override
    public void sendToken(HttpServletResponse response, String jwt) {
        response.setHeader(ACCESS_TOKEN_HEADER, jwt);
    }


    @Override
    public Optional<String> extractToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_TOKEN_HEADER));
    }


    @Override
    public boolean isValid(String jwt) {
        try {
            JWT.require(algorithm).build().verify(jwt);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
