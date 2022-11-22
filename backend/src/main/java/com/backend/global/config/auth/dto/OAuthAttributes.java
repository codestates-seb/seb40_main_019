package com.backend.global.config.auth.dto;

import com.backend.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * OAuth2 로그인 시 반환되는 사용자 정보
 */
@Getter
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String profileImage;
    private final String registrationId;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String profileImage, String registrationId) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.registrationId = registrationId;
    }

    /**
     * @param registrationId        OAuth2 로그인 진행 중인 서비스를 구분하는 코드
     *                              ex) google, naver, kakao
     * @param userNameAttributeName OAuth2 로그인 진행 시 키가 되는 필드값
     * @param attributes            OAuth2UserService를 통해 가져온 OAuth2User의 attribute
     *                              ex) name, email, picture
     * @return OAuthAttributes
     */
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        if ("naver".equals(registrationId)) {
            return ofNaver(registrationId, "id", attributes);
        }

        if ("kakao".equals(registrationId)) {
            return ofKakao(registrationId, "id", attributes);
        }

        return ofGoogle(registrationId, userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    private static OAuthAttributes ofNaver(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .profileImage((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    private static OAuthAttributes ofKakao(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuthAttributes.builder()
                .name((String) kakaoProfile.get("nickname"))
                .email((String) kakaoAccount.get("email"))
                .profileImage((String) kakaoProfile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .registrationId(registrationId)
                .build();
    }

    public User toEntity() {
        User user = new User();
        user.setUserRole("ROLE_USER");
        user.setProfileImage(profileImage);
        user.setEmail(email);
        user.setUsername(name + "[" + registrationId.toUpperCase() + "]");
        user.setSocialLogin(registrationId);
        user.setAbout("안녕하세요. " + name + "입니다.");

        return user;

    }

}
