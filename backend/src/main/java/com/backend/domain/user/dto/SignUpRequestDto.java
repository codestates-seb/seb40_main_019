package com.backend.domain.user.dto;

import com.backend.domain.user.domain.User;
import com.backend.domain.user.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    // TODO : validation 추가
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String username;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String profileImage;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;
    //    private String detailAddress;
    @NotBlank(message = "우편번호를 입력해주세요.")
    private String zipCode;

    // toEntity User
    public User toEntity() {
        return User.builder()
                .userName(username)
                .email(email)
                .password(password)
                .profileImage("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png") // TODO: 회원가입 프로필 이미지 랜덤
                .about("안녕하세요.")
                .userRole(UserRole.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}
