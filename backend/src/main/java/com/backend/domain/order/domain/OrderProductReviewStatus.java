package com.backend.domain.order.domain;

import lombok.Getter;

@Getter
public enum OrderProductReviewStatus {
    WRITING(1,"리뷰 작성가능"),
    WRITED(2, "리뷰 작성완료");

    @Getter
    private int statusNumber;

    @Getter
    private String statusName;

    OrderProductReviewStatus(int statusNumber, String statusName) {
        this.statusNumber = statusNumber;
        this.statusName = statusName;
    }
}

