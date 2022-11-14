package com.backend.domain.user.dto;

import com.backend.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReissueResponseDto {

    private String email;
    private String username;
    private String imageUrl;

    @Builder
    public ReissueResponseDto(String email, String imageUrl, String username) {
        this.email = email;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public static ReissueResponseDto toResponse(User user) {
        return ReissueResponseDto.builder()
                .email(user.getEmail())
                .imageUrl(user.getProfileImage())
                .username(user.getUserName())
                .build();
    }

}
