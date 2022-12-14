package com.backend.domain.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RequestSendMail {
        @NotBlank
        @Email
        private String email;
    }

    @Getter
    @Builder
    public static class ResponseMail {
        private String email;
    }

    @Getter
    @Builder
    public static class RequestMailWithPassword {
        private String email;
        private String password;
    }

}
