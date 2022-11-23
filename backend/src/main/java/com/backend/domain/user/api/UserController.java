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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    /**
     * 회원가입
     * @param userPostDto 회원가입 정보
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

        return ResponseEntity.ok(userService.createAccessToken(refreshToken, response));
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody UserPatchDto userPatchDto) {
        User user = mapper.userPatchDtoToUser(userService, userPatchDto);
        userService.updateUser(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> getDetails(@CurrentUser CustomUserDetails authUser) {
        User user = authUser.getUser();
        return ResponseEntity.ok(mapper.userToUserResponseDto(user));
    }

    /**
     * 로그아웃 시 토큰 삭제
     *
     * @param authUser 현재 유저
     */
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@CurrentUser CustomUserDetails authUser) {
        Long userId = authUser.getUser().getUserId();
        log.info("userId : {}", userId);

        userService.logout(userId);
        log.info("로그아웃 성공");
        return ResponseEntity.ok().build();
    }

    /**
     * 테스트 회원 생성
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
     * @return 생성된 관리자 정보
     */
    @GetMapping("/test/admin")
    public ResponseEntity<TestUserResponseDto> createTestAdmin() {

        String testAccountRole = "ROLE_ADMIN_TEST";

        TestUserResponseDto testUserResponseDto = userService.signupTestAccount(testAccountRole);

        return ResponseEntity.ok(testUserResponseDto);
    }

    @GetMapping("/social-user")
    public ResponseEntity<String> loginUserInfo(@CurrentUser CustomUserDetails authUser) {

        User user = authUser.getUser();
        String responseLoginUserInfo = userService.getLoginUserInfo(user);

        return ResponseEntity.ok(responseLoginUserInfo);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@CurrentUser CustomUserDetails authUser) {

        User user = authUser.getUser();
        userService.deleteUser(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/password/confirm")
    public ResponseEntity<Boolean> confirmUserPassword(@CurrentUser CustomUserDetails authUser,
                                                       @RequestBody PasswordDto password) {
        User user = authUser.getUser();
        boolean isCurrentPassword = userService.confirmUserPassword(user, password);

        return ResponseEntity.ok(isCurrentPassword);
    }

}
