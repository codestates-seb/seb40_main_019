package com.backend.domain.user.api;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.AuthUser;
import com.backend.domain.user.dto.UserPatchDto;
import com.backend.domain.user.dto.UserResponseDto;
import com.backend.global.annotation.CurrentMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PatchMapping
    public ResponseEntity<?> update(
            @CurrentMember AuthUser authUser,
            @RequestBody @Valid UserPatchDto userPatchDto) {

        Long result = userService.update(authUser.getUserId(), userPatchDto);

        return ResponseEntity.ok(authUser.getUserId());
    }

    @GetMapping("/me")

    public ResponseEntity<UserResponseDto> getMyMemberInfo(@CurrentMember AuthUser authUser) {
        Long memberId = authUser.getUserId();
        return ResponseEntity.ok(userService.getMyInfo(memberId));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getMemberInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.getMemberInfo(email));
    }
}
