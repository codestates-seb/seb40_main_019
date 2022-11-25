package com.backend.domain.user.dto;

import lombok.Getter;

@Getter
public class UserPatchDto {
    // todo : validation 추가

    private String nickname;

    private String password;

    private String profileImage;

//    private String about;

    private String address;

    private String zipCode;

    private String phone;

    private String username;

}
