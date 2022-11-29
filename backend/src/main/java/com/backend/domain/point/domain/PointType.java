package com.backend.domain.point.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum PointType {
    SignUpPoint(1, "회원가입 축하 포인트 추가"),
    AddPoint(2, "현금으로 포인트 충전"),
    PayPoint(3, "포인트로 상품 결제");

    private int statusNumber;

    @Getter
    private String statusName;

    PointType(int statusNumber, String statusName) {
        this.statusNumber = statusNumber;
        this.statusName = statusName;
    }

}
