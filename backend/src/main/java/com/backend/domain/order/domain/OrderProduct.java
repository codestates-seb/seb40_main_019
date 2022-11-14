package com.backend.domain.order.domain;

import com.backend.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    @Column(nullable = false)
    private int orderProductQuantity;//주문 당시의 수량

    @ManyToOne
    @JoinColumn(name = "oreder_id")
    private Order order;//주문정보

    private int totalPrice;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;//상품정보
}
    //상품정보


