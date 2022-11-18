package com.backend.global.config.auth.filter;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.UserLoginDto;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.utils.jwt.JwtTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
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

@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenizer jwtTokenizer;

    //로그인 요청을 하면 로그인 시도를 위해서 실행되는 함수 /login
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter:로그인 시도함");

        ObjectMapper om = new ObjectMapper();
        UserLoginDto userLoginDto = null;

        try {
            userLoginDto = om.readValue(request.getInputStream(),UserLoginDto.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        log.info("JwtAuthenticationFilter : " + userLoginDto);

        userService.verifyExistUserByEmailAndOriginal(userLoginDto.getEmail());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                );

        log.info("JwtAuthenticationFilter : 토큰생성완료");

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);//토큰을 통해 인증을 시도함

        CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();
        log.info("Authentication : " + customUserDetails.getUser().getEmail());

        return authenticate;
    }

    @Override
    @Transactional
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        String accessToken = delegateAccessToken(customUserDetails);
        String refreshToken = delegateRefreshToken(customUserDetails);


        response.setHeader("Authorization", "Bearer " + accessToken);

       Long refreshExp = (long) jwtTokenizer.getRefreshTokenExpirationMillisecond();

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(refreshExp)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();

        response.setHeader("Set-Cookie", cookie.toString());
    }

    private String delegateAccessToken(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        User user = customUserDetails.getUser();
        claims.put("userId", user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("userRole", user.getUserRole());

        String subject = user.getUserId().toString(); //Jwt 의 제목
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMillisecond());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    private String delegateRefreshToken(CustomUserDetails customUserDetails) {

        Map<String, Object> claims = new HashMap<>();
        User user = customUserDetails.getUser();
        claims.put("userId", user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("userRole", user.getUserRole());

        String subject = user.getUserId().toString(); //Jwt 의 제목
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMillisecond());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getRefreshSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(claims, subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
