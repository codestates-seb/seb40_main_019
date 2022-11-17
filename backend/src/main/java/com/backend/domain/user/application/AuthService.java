package com.backend.domain.user.application;


import com.backend.domain.refreshToken.dao.RefreshTokenRepository;
import com.backend.domain.refreshToken.domain.RefreshToken;
import com.backend.domain.refreshToken.exception.TokenInvalid;
import com.backend.domain.refreshToken.exception.TokenNotFound;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.Address;
import com.backend.domain.user.domain.AuthUser;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.domain.UserRole;
import com.backend.domain.user.dto.ReissueResponseDto;
import com.backend.domain.user.dto.SignUpRequestDto;
import com.backend.domain.user.dto.TestUserResponseDto;
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
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.backend.domain.user.domain.UserRole.ROLE_TESTUSER;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private Long guestId = 1L;
    private Long adminId = 1L;

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

    // 테스트 유저 회원가입
    @Transactional
    public TestUserResponseDto signupTestAccount(UserRole userRole) {

//        String randomEmail = createTestAccountEmail();
//        String randomUsername = createTestAccountUsername();

        String testRole = "";
        String testUserId = "";

        if (userRole == ROLE_TESTUSER) {
            testRole = "guest";
            testUserId = String.valueOf(guestId);
            hitGuest();
        } else {
            testRole = "admin";
            testUserId = String.valueOf(adminId);
            hitAdmin();
        }

        String guestEmail = testRole + testUserId + "@test.com";
        String guestUsername = testRole + testUserId;

        String randomPassword = createTestAccountPassword();
        List<Address> testAddress = List.of(Address.builder()
                .address("서울특별시 강남구 테헤란로 427")
                .zipCode("16164")
                .build());

        User testUser = User.builder()
                .email(guestEmail)
                .userName(guestUsername)
                .password(randomPassword)
                .about("안녕하세요. 테스트 계정입니다.")
                .userRole(userRole)
                .profileImage("https://i.ibb.co/7bQQYkX/kisspng-computer-icons-user-profile-avatar-5abcbc2a1f4f51-20180201102408184.png")
                .build();
        testUser
                .encodePassword(passwordEncoder);
        testUser.addAddress(testAddress.get(0));

        userRepository.save(testUser);

        return TestUserResponseDto.builder()
                .email(testUser.getEmail())
                .password(randomPassword)
                .build();
    }

    // 테스트용 계정 생성
//    public String createTestAccountEmail() {
//        StringBuffer key = new StringBuffer();
//        Random rnd = new Random();
//
//        for (int i = 0; i < 14; i++) {
//            int index = rnd.nextInt(2); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨
//
//            switch (index) {
//                case 0:
//                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
//                    // a~z (ex. 1+97=98 => (char)98 = 'b')
//                    break;
//                case 1:
//                    key.append((rnd.nextInt(10)));
//                    // 0~9
//                    break;
//            }
//        }
//        return key.toString() + "@test.com";
//    }

    // 테스트용 계정 비밀번호 생성
    public String createTestAccountPassword() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

//    public String createTestAccountUsername() {
//        StringBuffer key = new StringBuffer();
//        Random rnd = new Random();
//
//        for (int i = 0; i < 14; i++) {
//            int index = rnd.nextInt(2); // 0~2 까지 랜덤, rnd 값에 따라서 아래 switch 문이 실행됨
//
//            switch (index) {
//                case 0:
//                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
//                    // a~z (ex. 1+97=98 => (char)98 = 'b')
//                    break;
//                case 1:
//                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
//                    // A~Z
//                    break;
//            }
//        }
//        return key.toString() + "TEST";
//    }

    private void hitGuest() {
        guestId++;
    }

    private void hitAdmin() {
        adminId++;
    }
}
