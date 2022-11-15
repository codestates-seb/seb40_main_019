package com.backend.domain.order.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class OrderPostDto {
    @Positive
    private long userId; //유저id.. 토큰 받으면되나?

    private String receiverName;//수령인 이름

    private String receiverPhone;//수령인 번호

    private String receiverAddress;// 수령인 주소 - 좀 더 좋게 받을 수 있는 방법이 없을까?
    private List<OrderProductDto> orderProducts; //주문 품목들




   /* @Builder
    private OrderPostDto(long userId, String receiverName, String receiverPhone, String receiverAddress, List<OrderProductDto> orderProducts, Long productId) {
        this.userId = userId;
        this.orderProducts = orderProducts;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.receiverName = receiverName;
        this.productId = productId;
    }*/
}
// 1차완