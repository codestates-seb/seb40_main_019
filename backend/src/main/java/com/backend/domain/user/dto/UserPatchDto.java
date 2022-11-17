package com.backend.domain.user.dto;

import com.backend.domain.user.domain.Address;
import com.backend.domain.user.domain.User;
import lombok.Getter;

import javax.validation.constraints.Email;
import java.util.List;

@Getter
public class UserPatchDto {
    // todo : validation 추가

    private String username;

    @Email(message = "이메일 형식이 맞는 지 확인해주세요")
    private String email;

    private String password;

    private String profileImage;

    private String about;

    private List<Address> address;
    //회원 탈퇴
    private User.UserStatus userStatus;

}
