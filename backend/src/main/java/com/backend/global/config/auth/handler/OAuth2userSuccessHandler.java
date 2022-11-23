package com.backend.global.config.auth.handler;

import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.domain.RefreshToken;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.global.utils.jwt.JwtTokenizer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2 로그인 성공 시 수행
 */
@AllArgsConstructor
@Slf4j
public class OAuth2userSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtTokenizer jwtTokenizer;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    /**
     * @param request        요청
     * @param response       응답
     * @param authentication 인증
     */
    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {

        log.info("OAuth2userSuccessHandler 실행");

        var oAuth2User = (OAuth2User) authentication.getPrincipal();
        String registrationId = response.getHeader("registrationId");

        String email;

        log.info("authentication.getPrincipal():" + oAuth2User);
        if (registrationId.equals("kakao")) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
            email = String.valueOf(kakaoAccount.get("email"));
        } else {
            email = String.valueOf(oAuth2User.getAttributes().get("email"));
        }

        log.info("email:" + email + ", registrationId:" + registrationId);

        User user = userRepository.findByEmailAndUserStatusAndSocialLogin(email, User.UserStatus.USER_EXIST, registrationId).get();

        String accessToken = delegateAccessToken(user);
        String refreshToken = delegateRefreshToken(user);
        log.info("JWT 발급 완료");

        saveRefreshToken(refreshToken, user);
        log.info("RefreshToken 저장 완료");

        String uri = createURI(accessToken, refreshToken, registrationId).toString();


        getRedirectStrategy().sendRedirect(request, response, uri);

        log.info("OAuth2userSuccessHandler 종료");
    }

    private void saveRefreshToken(String refreshToken, User user) {
        Long userId = user.getUserId();
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .key(userId)
                .value(refreshToken)
                .build();

        refreshTokenRepository.save(refreshTokenEntity);
    }

    /**
     * URI 생성
     *
     * @param accessToken    액세스 토큰
     * @param refreshToken   리프레시 토큰
     * @param registrationId 소셜 로그인 구분
     * @return URI
     */
    private URI createURI(String accessToken, String refreshToken, String registrationId) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", "Bearer " + accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(3000)
                .path("/oauth/" + registrationId)
                .queryParams(queryParams)
                .build()
                .toUri();

        // todo 주소 변경
//        return UriComponentsBuilder
//                .newInstance()
//                .scheme("https")
//                .host("app")
//                .port(443)
//                .path("/oauth/" + registrationId)
//                .queryParams(queryParams)
//                .build()
//                .toUri();
    }

    // todo 토큰 발급 중복 메소드 통합 (JwtAuthenticationFilter, OAuth2userSuccessHandler)
    /**
     * 액세스 토큰 발급
     *
     * @param user 유저
     * @return 액세스 토큰
     */
    private String delegateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());

        String subject = user.getUserId().toString();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMillisecond());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getAccessSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    /**
     * 리프레시 토큰 발급
     *
     * @param user 유저
     * @return 리프레시 토큰
     */
    private String delegateRefreshToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());

        String subject = user.getUserId().toString();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMillisecond());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getRefreshSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(claims, subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }


}
