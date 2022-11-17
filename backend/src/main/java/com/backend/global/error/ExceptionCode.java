package com.backend.global.error;

import lombok.Getter;

public enum ExceptionCode {

    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, " Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),
    HANDLE_ACCESS_DENIED(403, "Access is Denied"),
    // User
    EMAIL_DUPLICATION(409, "Email Is Duplication"),
    USER_NOT_FOUND(404, "USER NOT FOUND"),
    USERNAME_DUPLICATION(409, "UserName Is Duplicated"),
    LOGIN_FAILED(400, "Login input is invalid"),
    NOT_LOGIN_MEMBER(400, "Not Login Member"),

    // Token
    TOKEN_NOT_FOUND(400, "Token Not Found"),
    TOKEN_EXPIRED(400, "Token Expired"),
    TOKEN_INVALID(400, "Token Invalid"),
    TOKEN_SIGNATURE_INVALID(400, "Token Signature Invalid"),
    TOKEN_MALFORMED(400, "Token Malformed"),
    TOKEN_UNSUPPORTED(400, "Token Unsupported"),
    TOKEN_ILLEGAL_ARGUMENT(400, "Token Illegal Argument"),


    ANSWER_NOT_FOUND(404, "ANSWER_NOT_FOUND"),

    POST_NOT_FOUND(404, "POST NOT FOUND"),

    // Product
    PRODUCT_EXIST(409,"PRODUCT EXIST"),
    PRODUCT_NOT_FOUND(404,"PRODUCT_NOT_FOUND"),

    // Review
    REVIEW_NOT_FOUND(404,"REVIEW_NOT_FOUND");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
