package com.backend.domain.user.api;


import com.backend.domain.user.application.RegisterMail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.backend.domain.user.dto.EmailDto.RequestSendMail;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class SendMail {

    private final RegisterMail registerMail;

    // 이메일 인증
    @ResponseBody
    @PostMapping("/confirm")
    String mailConfirm(@RequestBody RequestSendMail emailDto) throws Exception {

        String email = emailDto.getEmail();

        String code = registerMail.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }
}
