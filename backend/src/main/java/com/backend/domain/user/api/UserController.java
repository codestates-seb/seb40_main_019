package com.backend.domain.user.api;

import com.backend.domain.user.application.UserService;
import com.backend.domain.user.dto.UserPatchDto;
import com.backend.global.annotation.CurrentMember;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PatchMapping
    public ResponseEntity<?> update(
            @CurrentMember CustomUserDetails authUser,
            @RequestBody @Valid UserPatchDto userPatchDto) {

        Long result = userService.update(authUser.getUser().getUserId(), userPatchDto);

        return ResponseEntity.ok(authUser.getUser().getUserId());
    }

//    @GetMapping("/me")
//    public ResponseEntity<UserResponseDto> getMyMemberInfo(@CurrentMember CustomUserDetails authUser) {
//        Long memberId = authUser.getUser().getUserId();
//        return ResponseEntity.ok(userService.getMyInfo(memberId));
//    }
//
//    @GetMapping("/{email}")
//    public ResponseEntity<UserResponseDto> getMemberInfo(@PathVariable String email) {
//        return ResponseEntity.ok(userService.getMemberInfo(email));
//    }
}
