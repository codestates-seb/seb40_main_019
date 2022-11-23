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
    private String email;
    private String nickname;
    private String profileImage;
    private String about;
    private String userRole;
    private String socialLogin;
    private User.UserStatus userStatus;
    private String address;
    private String zipCode;
    private String phone;
    private String username;

}
