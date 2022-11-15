package com.backend.domain.user.application;


import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.domain.RefreshToken;
import com.backend.domain.refreshToken.exception.TokenInvalid;
import com.backend.domain.refreshToken.exception.TokenNotFound;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.Address;
import com.backend.domain.user.domain.AuthUser;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.ReissueResponseDto;
import com.backend.domain.user.dto.SignUpRequestDto;
import com.backend.domain.user.dto.TokenDto;
import com.backend.domain.user.exception.EmailDuplication;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.domain.user.exception.NotLoginMember;
import com.backend.domain.user.exception.UserNameDuplication;
import com.backend.global.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입
    @Transactional
    public void signup(SignUpRequestDto signUpRequestDto) {

        verifyExistsEmail(signUpRequestDto.getEmail());
        verifyExistsUsername(signUpRequestDto.getUsername());

        // 비밀번호 암호화 후 저장
        User user = signUpRequestDto.toEntity();
        user.encodePassword(passwordEncoder);

        Address address = Address.builder()
                .address(signUpRequestDto.getAddress())
                .zipCode(signUpRequestDto.getZipCode())
                .build();
        user.addAddress(address);

        userRepository.save(user);
    }

    private void verifyExistsEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailDuplication();
        }
    }

    private void verifyExistsUsername(String username) {
        if (userRepository.existsByUserName(username)) {
            throw new UserNameDuplication();
        }
    }

    // 토큰 재발급
    @Transactional
    public ReissueResponseDto reissue(String refreshToken,
                                      HttpServletResponse response) {

        refreshToken = Optional.ofNullable(refreshToken)
                .orElseThrow(TokenNotFound::new);

        Claims claims = tokenProvider.parseClaims(refreshToken);


        User user = userRepository.findById(Long.parseLong(claims.getSubject()))
                .orElseThrow(MemberNotFound::new);

        AuthUser authMember = AuthUser.of(user);

        Long memberId = authMember.getUserId();

        TokenDto tokenDto = tokenProvider.generateTokenDto(authMember);
        String newRTK = tokenDto.getRefreshToken();
        String newATK = tokenDto.getAccessToken();

        RefreshToken savedRefreshToken = refreshTokenRepository.findById(memberId)
                .orElseThrow(NotLoginMember::new);

        if (!savedRefreshToken.getValue().equals(refreshToken)) {
            throw new TokenInvalid();
        }

        RefreshToken newRefreshToken = savedRefreshToken.updateValue(newRTK);
        refreshTokenRepository.save(newRefreshToken);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", newRTK)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());


        response.setHeader("Authorization", "Bearer " + newATK);

        return ReissueResponseDto.toResponse(user);
    }

    // 로그아웃
    @Transactional
    public void logout(String refreshToken, HttpServletRequest request, HttpServletResponse response) {

        refreshToken = Optional.ofNullable(refreshToken)
                .orElseThrow(TokenNotFound::new);
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(0)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();
        response.setHeader("Set-Cookie", cookie.toString());

        refreshTokenRepository.deleteByKey(Long.valueOf(tokenProvider.parseClaims(refreshToken).getSubject()));
    }

}
