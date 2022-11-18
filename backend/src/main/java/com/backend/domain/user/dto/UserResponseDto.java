package com.backend.domain.user.dto;

import com.backend.domain.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    private Long userId;
    private String username;
    private String email;
    private String profileImage;
    private User.UserStatus userStatus;
    private String userRole;
    private String socialLogin;

}
