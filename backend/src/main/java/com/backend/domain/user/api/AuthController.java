package com.backend.domain.user.api;


import com.backend.domain.user.application.AuthService;
import com.backend.domain.user.domain.UserRole;
import com.backend.domain.user.dto.ReissueResponseDto;
import com.backend.domain.user.dto.SignUpRequestDto;
import com.backend.domain.user.dto.TestUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping()
    public ResponseEntity<?> signup(@RequestBody SignUpRequestDto signUpRequestDto) {

        authService.signup(signUpRequestDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/login")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    // 재발급
    @GetMapping("/reissue")
    public ResponseEntity<ReissueResponseDto> reissue(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                                      HttpServletResponse response) {
        return ResponseEntity.ok(authService.reissue(refreshToken, response));
    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@CookieValue(value = "refreshToken", required = false) String refreshToken,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        authService.logout(refreshToken, request, response);
        return ResponseEntity.ok().build();
    }

    // test account 생성
    @GetMapping("/test/user")
    public ResponseEntity<TestUserResponseDto> createTestUser() {

        UserRole userRole = UserRole.ROLE_TESTUSER;

        TestUserResponseDto testUserResponseDto = authService.signupTestAccount(userRole);

        return ResponseEntity.ok(testUserResponseDto);
    }

    @GetMapping("/test/admin")
    public ResponseEntity<TestUserResponseDto> createTestAdmin() {

        UserRole userRole = UserRole.ROLE_TESTADMIN;

        TestUserResponseDto testUserResponseDto = authService.signupTestAccount(userRole);

        return ResponseEntity.ok(testUserResponseDto);
    }
}
