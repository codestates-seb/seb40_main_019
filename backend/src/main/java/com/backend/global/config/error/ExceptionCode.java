package com.backend.global.config.error;

import lombok.Getter;

public enum ExceptionCode {
    USER_NOT_FOUND(404, "USER NOT FOUND"),

    USER_EXIST(409, "USER EXIST"),

    ANSWER_NOT_FOUND(404,"ANSWER_NOT_FOUND"),

    POST_NOT_FOUND(404,"POST NOT FOUND");
    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
