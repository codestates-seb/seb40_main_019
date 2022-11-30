package com.backend.global.config.auth.handler;

import com.backend.domain.point.application.PointService;
import com.backend.domain.point.domain.PointType;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.global.config.auth.dto.OAuthAttributes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * OAuth2UserService 인터페이스를 구현한 클래스
 *
 */
@AllArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PointService pointService;
    private HttpServletResponse response;

    /**
     * registrationId: 소셜 로그인 서비스 구분
     * userNameAttributeName: 소셜 로그인 서비스에서 회원을 구분할 수 있는 고유한 아이디를 지정
     * attributes: OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 클래스
     *
     * @param userRequest OAuth2UserRequest
     * @return OAuth2User
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserService 실행");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        log.info("oAuth2User: {}", oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        response.setHeader("registrationId", registrationId);

        log.info("registrationId: {}", registrationId);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attribute = oAuth2User.getAttributes();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, attribute);
        log.info("attributes: {}", attributes);

        User user = saveOrUpdate(attributes);

        OAuth2User printUser = new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());

        log.info("printUser: {}", printUser);

        return printUser;
    }

    /**
     * 소셜 로그인으로 회원가입을 할 경우, 기존 회원이면 업데이트, 신규 회원이면 저장
     *
     * @param attributes OAuthAttributes
     * @return User
     */
    private User saveOrUpdate(OAuthAttributes attributes) {
        log.info("saveOrUpdate 실행");
        User user = userRepository.findByEmailAndUserStatusAndSocialLogin(attributes.getEmail(), User.UserStatus.USER_EXIST, attributes.getRegistrationId())
                .orElse(attributes.toEntity(pointService, passwordEncoder));
        User save = userRepository.save(user);

        log.info("saved User");
        return save;
    }

}
