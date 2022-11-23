package com.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDto {

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 맞는 지 확인해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

}
