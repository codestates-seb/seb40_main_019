package com.backend.domain.user.dto;

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
}
