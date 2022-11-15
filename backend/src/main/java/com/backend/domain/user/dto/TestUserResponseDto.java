package com.backend.domain.user.dto;

import com.backend.domain.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestUserResponseDto {

    private String email;
    private String password;

    @Builder
    public TestUserResponseDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static TestUserResponseDto toResponse(User user) {
        return TestUserResponseDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
