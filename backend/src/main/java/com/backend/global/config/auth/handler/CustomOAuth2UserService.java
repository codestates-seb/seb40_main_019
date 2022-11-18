package com.backend.global.config.auth.handler;

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

@AllArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private HttpServletResponse response;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        log.info("loader 에서의 oAuth2User" + oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        response.setHeader("registrationId", registrationId);

        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService
        Map<String, Object> attribute = oAuth2User.getAttributes();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, attribute);
        User user = saveOrUpdate(attributes);

        OAuth2User printUser = new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());

        log.info("loadUser 실행 완료");

        return printUser;
    }


    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmailAndUserStatusAndSocialLogin(attributes.getEmail(), User.UserStatus.USER_EXIST, attributes.getRegistrationId())
                .orElse(attributes.toEntity());

        if (user.getPassword() == null) {
            user.setPassword(passwordEncoder.encode(user.getUsername()));
        }

        user.encodePassword(passwordEncoder);
        return userRepository.save(user);
    }

}
