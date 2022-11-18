package com.backend.domain.user.api;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.TestUserResponseDto;
import com.backend.domain.user.dto.UserLoginResponseDto;
import com.backend.domain.user.dto.UserPostDto;
import com.backend.domain.user.mapper.UserMapper;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    // 회원가입
    @PostMapping()
    public ResponseEntity<?> signup(@RequestBody UserPostDto userPostDto) {

        User user = mapper.userPostDtoToUser(userPostDto);
        User createdUser = userService.createUser(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    // 토큰 재발급
    @GetMapping("/reissue")
    public ResponseEntity<UserLoginResponseDto> reissue(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                                        HttpServletResponse response) {

        // todo 토큰 재발급 로직 수정
        // userId 로 RTK 조회 후, RTK 만료시간 확인
        return ResponseEntity.ok(userService.createAccessToken(refreshToken, response));
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response,
                                       @CurrentUser CustomUserDetails user) {
        Long userId = user.getUser().getUserId();
        userService.logout(response, userId);
        return ResponseEntity.ok().build();
    }

    // test account 생성
    @GetMapping("/test/user")
    public ResponseEntity<TestUserResponseDto> createTestUser() {

       String testAccountRole = "ROLE_USER_TEST";

        TestUserResponseDto testUserResponseDto = userService.signupTestAccount(testAccountRole);

        return ResponseEntity.ok(testUserResponseDto);
    }

    @GetMapping("/test/admin")
    public ResponseEntity<TestUserResponseDto> createTestAdmin() {

        String testAccountRole = "ROLE_ADMIN_TEST";

        TestUserResponseDto testUserResponseDto = userService.signupTestAccount(testAccountRole);

        return ResponseEntity.ok(testUserResponseDto);
    }


}
