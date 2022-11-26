package com.backend.domain.user.api;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.*;
import com.backend.domain.user.mapper.UserMapper;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    /**
     * 회원가입
     *
     * @param userPostDto 회원가입 정보
     *                    email, password, nickname
     */
    @PostMapping()
    public ResponseEntity<?> signup(@RequestBody UserPostDto userPostDto) {

        User user = mapper.userPostDtoToUser(userPostDto);

        userService.createUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    /**
     * 회원정보 수정
     *
     * @param userPatchDto 회원정보 수정 정보
     *                     (nickname, password, zipCode, address, phone, username)
     */
    @PatchMapping
    public ResponseEntity<?> update(@RequestBody UserPatchDto userPatchDto) {

        User user = mapper.userPatchDtoToUser(userService, userPatchDto);
        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    /**
     * 회원 정보 상세 조회
     *
     * @param authUser 현재 로그인한 유저
     * @return 회원 정보
     */
    @GetMapping
    public ResponseEntity<UserResponseDto> detailsUserInfo(@CurrentUser CustomUserDetails authUser) {

        User user = authUser.getUser();
        UserResponseDto responseDto = mapper.userToUserResponseDto(user);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * 현재 로그인한 유저의 정보 조회
     *
     * @param authUser 현재 로그인한 유저
     * @return 현재 로그인한 유저의 정보
     */
    @GetMapping("/social-user")
    public ResponseEntity<String> simpleUserInfo(@CurrentUser CustomUserDetails authUser) {

        User user = authUser.getUser();
        String responseLoginUserInfo = userService.getLoginUserInfo(user);

        return ResponseEntity.ok(responseLoginUserInfo);
    }

    /**
     * 유저 탈퇴 (Soft Delete)
     *
     * @param authUser 현재 로그인한 유저
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@CurrentUser CustomUserDetails authUser) {

        User user = authUser.getUser();
        userService.deleteUser(user);

        return ResponseEntity.ok().build();
    }

    /**
     * 로그아웃
     *
     * @param authUser 현재 유저
     */
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@CurrentUser CustomUserDetails authUser) {

        Long userId = authUser.getUser().getUserId();
        userService.logout(userId);

        return ResponseEntity.ok().build();
    }

    /**
     * Refresh Token을 이용하여 Access Token을 재발급 받는다.
     *
     * @param request  Refresh Token을 담고 있는 HttpServletRequest
     * @param response Access Token을 담아 반환할 HttpServletResponse
     * @return 재발급 받은 User 정보
     */
    @GetMapping("/reissue")
    public ResponseEntity<UserLoginResponseDto> reissue(HttpServletRequest request,
                                                        HttpServletResponse response) {

        String refreshToken = request.getHeader("refreshToken");
        UserLoginResponseDto responseDto = userService.createAccessToken(refreshToken, response);

        return ResponseEntity.ok(responseDto);
    }

    /**
     * 테스트 회원 생성
     *
     * @return 생성된 회원 정보
     */
    @GetMapping("/test/user")
    public ResponseEntity<TestUserResponseDto> createTestUser() {

        String testAccountRole = "ROLE_USER_TEST";
        TestUserResponseDto testUserResponseDto = userService.signupTestAccount(testAccountRole);

        return ResponseEntity.ok(testUserResponseDto);
    }

    /**
     * 테스트 관리자 생성
     *
     * @return 생성된 관리자 정보
     */
    @GetMapping("/test/admin")
    public ResponseEntity<TestUserResponseDto> createTestAdmin() {

        String testAccountRole = "ROLE_ADMIN_TEST";
        TestUserResponseDto testUserResponseDto = userService.signupTestAccount(testAccountRole);

        return ResponseEntity.ok(testUserResponseDto);
    }

    /**
     * 입력한 비밀번호 일치 여부 확인
     *
     * @param authUser 현재 로그인한 유저
     * @param password 비교할 비밀번호
     * @return 비밀번호 일치 여부
     */
    @PostMapping("/password/confirm")
    public ResponseEntity<Boolean> comparePassword(@CurrentUser CustomUserDetails authUser,
                                                   @RequestBody PasswordDto password) {

        return ResponseEntity.ok(userService.comparePassword(authUser.getUser(), password));
    }

    /**
     * 번호로 아이디(이메일) 찾기
     *
     * @param phoneNumberDto 찾을 아이디(이메일)의 번호
     * @return 아이디(이메일)
     */
    @PostMapping("/find-id")
    public ResponseEntity<EmailDto.ResponseMail> findIdByPhoneNumber(@RequestBody PhoneNumberDto phoneNumberDto) {

        String phoneNumber = phoneNumberDto.getPhoneNumber();

        EmailDto.ResponseMail response = EmailDto.ResponseMail.builder()
                .email(userService.findIdByPhoneNumber(phoneNumber))
                .build();

        return ResponseEntity.ok(response);
    }

    /**
     * 새로운 비밀번호 설정
     *
     * @param emailDto 이메일, 새로운 비밀번호
     */
    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody EmailDto.RequestMailWithPassword emailDto) throws Exception {

        log.info("새 비밀번호 발급 API 요청" +
                "\n이메일 : " + emailDto.getEmail() +
                "\n새 비밀번호 : " + emailDto.getPassword());
        String email = emailDto.getEmail();
        String password = emailDto.getPassword();

        userService.newPassword(email, password);

        log.info("새 비밀번호 발급 완료");

        return ResponseEntity.ok().build();
    }

}
