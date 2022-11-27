package com.backend.domain.order.domain;

import lombok.Getter;

@Getter
public enum OrderProductReviewStatus {
    WRITING(1,"상품 준비중"),
    WRITED(2, "배송중");

    @Getter
    private int statusNumber;

    @Getter
    private String statusName;

    OrderProductReviewStatus(int statusNumber, String statusName) {
        this.statusNumber = statusNumber;
        this.statusName = statusName;
    }
}

