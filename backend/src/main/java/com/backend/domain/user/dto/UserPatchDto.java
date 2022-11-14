package com.backend.domain.user.dto;


import com.backend.domain.user.domain.Address;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPatchDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String profileImage;

    private String about;

    private List<Address> address;

    @Builder
    public UserPatchDto(String password, String username, String profileImage, String about, List<Address> address) {
        this.password = password;
        this.username = username;
        this.profileImage = profileImage;
        this.about = about;
        this.address = address;
    }
}
