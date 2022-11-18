package com.backend.global.config.auth.filter;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.UserLoginDto;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.utils.jwt.JwtTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// 로그인 요청에서 username,password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작을 함
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // /login 요청이 오면 -> UsernamePass~Filter가 가로채서 attemptAuthentication 함수가 자동 실행)
    // Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
    // 인증 요청시에 실행되는 함수 => /login
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenizer jwtTokenizer;
    private final UserRepository userRepository;

    //로그인 요청을 하면 로그인 시도를 위해서 실행되는 함수 /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter:로그인 시도함");

        ObjectMapper om = new ObjectMapper();
        UserLoginDto userLoginDto =null;

        try {
            userLoginDto = om.readValue(request.getInputStream(),UserLoginDto.class);

        } catch (Exception e){
            e.printStackTrace();
        }

        log.info("JwtAuthenticationFilter : "+userLoginDto);

        userService.verifyExistUserByEmailAndOriginal(userLoginDto.getEmail()); //현재 활동중인 일반 회원가입으로 가입한 유저중 email 파라미터로 조회
        // DB에 없는 유저거나 이전에 탈퇴한 유저면 예외처리함

        //(3-3)User email과 Password 정보를 포함한 UsernamePasswordAuthenticationToken을 생성
        UsernamePasswordAuthenticationToken authenticationToken=
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                );

        log.info("JwtAuthenticationFilter : 토큰생성완료");

        Authentication authentication = authenticationManager.
                authenticate(authenticationToken);// UsernamePasswordAuthenticationToken을 AuthenticationManager에게 전달하면서 인증 처리를 위임

        CustomUserDetails customUserDetails = (CustomUserDetails)authentication.
                getPrincipal();
        log.info("Authentication : "+ customUserDetails.getUser().getEmail());

        return authentication;
    }
    // attemptAuthentication 위 함수 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 실행됌
    // JWT Token 생성해서 response에 담아주기
    @Override
    @Transactional
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();// (4-1)authResult.getPrincipal()로 User 엔티티 클래스의 객체를 얻습니다.

        String accessToken = delegateAccessToken(customUserDetails);
        String refreshToken = delegateRefreshToken(customUserDetails);


        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("Refresh", refreshToken);
    }

    // (5)
    private String delegateAccessToken(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        User user = customUserDetails.getUser();
        claims.put("userId",user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("userRole", user.getUserRole());

        String subject = user.getUserId().toString(); //Jwt 의 제목
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    // (6)
    private String delegateRefreshToken(CustomUserDetails customUserDetails) {

        Map<String, Object> claims = new HashMap<>();
        User user = customUserDetails.getUser();
        claims.put("userId",user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("userRole", user.getUserRole());

        String subject = user.getUserId().toString(); //Jwt 의 제목
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getRefreshSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(claims,subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
