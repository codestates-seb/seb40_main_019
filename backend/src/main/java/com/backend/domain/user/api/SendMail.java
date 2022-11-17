package com.backend.domain.user.api;


import com.backend.domain.user.application.AuthService;
import com.backend.domain.user.application.RegisterMail;
import com.backend.domain.user.dto.EmailDto;
import com.backend.domain.user.dto.ReissueResponseDto;
import com.backend.domain.user.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import static com.backend.domain.user.dto.EmailDto.*;

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
