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

    @Builder
    public UserLoginResponseDto(String email, String imageUrl, String nickname) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.nickname = nickname;
    }

    public static UserLoginResponseDto toResponse(User user) {
        return UserLoginResponseDto.builder()
                .email(user.getEmail())
                .imageUrl(user.getProfileImage())
                .nickname(user.getNickname())
                .build();
    }

}
