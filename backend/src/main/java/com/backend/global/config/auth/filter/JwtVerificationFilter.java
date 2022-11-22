package com.backend.global.config.auth.filter;


import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.utils.jwt.JwtTokenizer;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * JWT 인증을 담당
 */
@AllArgsConstructor
@Slf4j
public class JwtVerificationFilter extends OncePerRequestFilter {

    private final JwtTokenizer jwtTokenizer;
    private final UserRepository userRepository;


    /**
     * JWT 토큰을 검증하는 함수
     *
     * @param request     요청
     * @param response    응답
     * @param filterChain 필터 체인
     */
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) {
        log.info("JwtVerificationFilter 실행");

        try {
            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);

        } catch (SignatureException se) {
            throw new JwtException("사용자 인증 실패", se);
        } catch (ExpiredJwtException ee) {
            throw new JwtException("토큰 기한 만료", ee);
        } catch (Exception e) {
            throw new JwtException("토큰 검증 실패", e);
        }

        filterChain.doFilter(request, response);

        log.info("JwtVerificationFilter 종료");
    }

    /**
     * 액세스 토큰을 검증하는 함수
     *
     * @param request 요청
     * @return 토큰 검증 결과
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        log.info("JwtVerificationFilter - shouldNotFilter");

        String authorization = request.getHeader("Authorization");
        boolean bearer = authorization == null || !authorization.startsWith("Bearer");

        log.info("shouldNotFilter : {}", bearer);

        return bearer;
    }

    /**
     * request 에서 claims 를 추출하는 함수
     *
     * @param request 요청
     * @return 토큰 검증 결과
     */
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        log.info("JwtVerificationFilter - verifyJws");

        String jws = request.getHeader("Authorization").replace("Bearer ", "");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());

        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        log.info("verifyJws 통과");

        return claims;
    }

    /**
     * SecurityContext 에 Authentication 을 저장하는 함수
     *
     * @param claims 토큰 검증 결과
     */
    private void setAuthenticationToContext(Map<String, Object> claims) {
        log.info("JwtVerificationFilter - setAuthenticationToContext");

        Long userId = Long.parseLong(claims.get("userId").toString());
        log.info("서명이 정상적으로 됌" + userId);
        User user = userRepository.findById(userId).get();

        CustomUserDetails memberDetails = new CustomUserDetails(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        log.info("setAuthenticationToContext 통과");
    }
}
