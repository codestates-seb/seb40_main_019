package com.backend.global.config.auth.filter;

import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.domain.RefreshToken;
import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.UserLoginDto;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.utils.jwt.JwtTokenizer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * /login 요청하면 로그인 시도를 위해 실행되는 필터
 * 1. /login 요청해서 username, password 전송하면 (post)
 * 2. UsernamePasswordAuthenticationFilter 동작
 * 3. attemptAuthentication 실행 -> 로그인 시도를 위해 실행됨
 * 4. 로그인 성공하면 successfulAuthentication 실행
 * 5. JWT 토큰 만들어서 request 의 header, cookie 에 담아줌
 */
@Slf4j
@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenizer jwtTokenizer;
    private final RefreshTokenRepository refreshTokenRepository;


    /**
     * /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
     *
     * @param request  요청
     * @param response 응답
     * @return Authentication
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        log.info("JwtAuthenticationFilter: 로그인 시도중");

        ObjectMapper om = new ObjectMapper();
        UserLoginDto userLoginDto = null;

        userLoginDto = om.readValue(request.getInputStream(), UserLoginDto.class);

        log.info("JwtAuthenticationFilter : " + userLoginDto);

        userService.verifyExistUserByEmailAndOriginal(userLoginDto.getEmail());

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),
                        userLoginDto.getPassword()
                );

        log.info("JwtAuthenticationFilter : authenticationToken 생성완료");

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        CustomUserDetails customUserDetails = (CustomUserDetails) authenticate.getPrincipal();
        log.info("Authentication : " + customUserDetails.getUser().getEmail());

        return authenticate;
    }


    /**
     * 로그인 성공하면 실행되는 함수
     *
     * @param request    요청
     * @param response   응답
     * @param chain      필터 체인
     * @param authResult 인증 결과
     */
    @SneakyThrows
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        log.info("JwtAuthenticationFilter: 로그인 성공");

        CustomUserDetails customUserDetails = (CustomUserDetails) authResult.getPrincipal();

        User user = customUserDetails.getUser();

        String accessToken = delegateAccessToken(user);
        log.info("JwtAuthenticationFilter: accessToken 생성완료");
        String refreshToken = delegateRefreshToken(user);
        log.info("JwtAuthenticationFilter: refreshToken 생성완료");


        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("refreshToken", refreshToken);

        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .key(user.getUserId())
                .value(refreshToken)
                .expirationDate(jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMillisecond()))
                .build();
        refreshTokenRepository.save(refreshTokenEntity);
        log.info("JwtAuthenticationFilter: refreshToken DB 저장완료");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"email\":\"" + user.getEmail() + "\"," +
                "\"nickname\":\"" + user.getNickname() + "\"," +
                "\"userRole\":\"" + user.getUserRole() + "\"," +
                "\"imageUrl\":\"" + user.getProfileImage() + "\"}");
    }

    /**
     * accessToken 생성
     *
     * @param user 유저
     * @return accessToken
     */
    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("userRole", user.getUserRole());

        String subject = user.getUserId().toString();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMillisecond());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    /**
     * refreshToken 생성
     *
     * @param user 유저
     * @return refreshToken
     */
    private String delegateRefreshToken(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("userRole", user.getUserRole());

        String subject = user.getUserId().toString();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMillisecond());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getRefreshSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(claims, subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }
}
