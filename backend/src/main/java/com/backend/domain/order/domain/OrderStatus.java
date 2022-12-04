package com.backend.domain.order.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PROCESS(1,"상품 준비중"),
    SHIPPING(2, "배송중"),
    SHIPPED(3, "배송 완료"),
    CANCEL(4,"주문 취소");


    @Getter
    private int statusNumber;

    @Getter
    private String statusName;

    OrderStatus(int statusNumber, String statusName) {
        this.statusNumber = statusNumber;
        this.statusName = statusName;
    }
}
