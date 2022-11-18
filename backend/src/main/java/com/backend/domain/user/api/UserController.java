package com.backend.domain.user.api;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.dto.ReissueResponseDto;
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
    public ResponseEntity<ReissueResponseDto> reissue(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                                      HttpServletResponse response) {

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
//    @GetMapping("/test/user")
//    public ResponseEntity<TestUserResponseDto> createTestUser() {
//
//        UserRole userRole = UserRole.ROLE_TESTUSER;
//
//        TestUserResponseDto testUserResponseDto = userService.signupTestAccount(userRole);
//
//        return ResponseEntity.ok(testUserResponseDto);
//    }
//
//    @GetMapping("/test/admin")
//    public ResponseEntity<TestUserResponseDto> createTestAdmin() {
//
//        UserRole userRole = UserRole.ROLE_TESTADMIN;
//
//        TestUserResponseDto testUserResponseDto = userService.signupTestAccount(userRole);
//
//        return ResponseEntity.ok(testUserResponseDto);
//    }


}
