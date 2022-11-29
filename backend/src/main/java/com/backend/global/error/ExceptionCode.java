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
    ALRREADY_LOGOUT(400, "ALREADY LOGOUT"),
    NICKNAME_DUPLICATION(409, "Nickname Is Duplicated"),
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

    CATEGORY_NOT_FOUND(404,"CATEGORY_NOT_FOUND"),

    // image
    No_Image(404,"Image Empty"),
    Upload_Failed(404,"Upload Failed"),

    // Review
    REVIEW_NOT_FOUND(404,"REVIEW_NOT_FOUND"),
    REVIEW_DUPLICATION(409,"Review Is Duplicated"),
    //Order
    ORDER_NOT_FOUND(404, "Order not found"),
    CANNOT_CHANGE_ORDER(403, "Order can not change"),
    CANNOT_CANCEL_ORDER(403, "Order can not cancel"),

    //Point
    NOT_ENOUGH_POINT(403,"Point is not enough");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
