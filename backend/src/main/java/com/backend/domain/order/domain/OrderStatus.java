package com.backend.domain.order.domain;

import lombok.Getter;

public enum OrderStatus {
    //ORDER(결제중), COMP(결제완료), CANCEL(결제취소), FAIL(결제오류), DELIVERY(배송중)
    //ORDER, COMP, CANCEL, FAIL, DELIVERY
    ORDER(1,"결제중"),
    COMP(2, "결제 완료"),
    CANCEL(3, "결제 취소"),
    FAIL(4, "결제 오류"),
    READY(5, "상품 준비중"),
    DELIVERY(6, "배송중"),
    DELIVERYCOMP(7, "배송 완료");

    @Getter
    private int statusNumber;

    @Getter
    private String statusName;

    OrderStatus(int statusNumber, String statusName) {
        this.statusNumber = statusNumber;
        this.statusName = statusName;
    }
}
