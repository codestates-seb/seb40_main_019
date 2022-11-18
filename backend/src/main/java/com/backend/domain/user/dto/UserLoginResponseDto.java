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
    private String username;
    private String imageUrl;

    @Builder
    public UserLoginResponseDto(String email, String imageUrl, String username) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public static UserLoginResponseDto toResponse(User user) {
        return UserLoginResponseDto.builder()
                .email(user.getEmail())
                .imageUrl(user.getProfileImage())
                .username(user.getUsername())
                .build();
    }

}
