package com.backend.domain.user.api;


import com.backend.domain.user.application.EmailService;
import com.backend.domain.user.application.UserService;
import com.backend.domain.user.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.backend.domain.user.dto.EmailDto.RequestSendMail;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final EmailService emailService;
    private final UserService userService;

    // 이메일 인증
    @ResponseBody
    @PostMapping("/confirm")
    String mailConfirm(@RequestBody RequestSendMail emailDto) throws Exception {
        String email = emailDto.getEmail();
        log.info("회원가입 인증메일 발송 시작);");
        log.info("email : {}", email);

        String code = emailService.sendSimpleMessage(email, "회원가입");
        log.info("인증코드 : " + code);

        return code;
    }

    @ResponseBody
    @PostMapping("/registered")
    String mailConfirmForUser(@RequestBody RequestSendMail emailDto) throws Exception {
        log.info("회원 찾기 메일 발송 시작");
        String email = emailDto.getEmail();
        log.info("email : {}", email);
        userService.getUserByEmail(email);
        log.info("가입된 이메일입니다.");
        String code = emailService.sendSimpleMessage(email, "회원 정보");
        log.info("인증코드 : " + code);

        return code;
    }

}
