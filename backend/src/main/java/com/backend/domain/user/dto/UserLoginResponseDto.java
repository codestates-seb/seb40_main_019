package com.backend.domain.user.dto;

import com.backend.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginResponseDto {
    private String email;
    private String nickname;
    private String imageUrl;
    private String userRole;

    @Builder
    public UserLoginResponseDto(String email, String imageUrl, String nickname, String userRole) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
        this.userRole = userRole;
    }

    public static UserLoginResponseDto toResponse(User user) {
        return UserLoginResponseDto.builder()
                .email(user.getEmail())
                .imageUrl(user.getProfileImage())
                .nickname(user.getNickname())
                .userRole(user.getUserRole())
                .build();
    }

}
