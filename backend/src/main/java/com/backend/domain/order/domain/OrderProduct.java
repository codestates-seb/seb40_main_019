package com.backend.domain.order.domain;

import com.backend.domain.order.dto.OrderPostDto;
import com.backend.domain.order.dto.OrderProductDto;
import com.backend.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Builder;
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

    private int price; // 주문개별가격

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;//상품정보

    public int getTotalPrice() {
        return price*orderProductQuantity;
    }

    public void setOrder(Order order){
        this.order = order;
    }



    /*@Builder
    public OrderProduct(Product product, int orderProductQuantity, Order order, int price){
        this.product = product;
        this.orderProductQuantity = orderProductQuantity;
        this.order = order;
        this.price = price;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static OrderProduct createOrderProduct(OrderProductDto orderProductDto, Product product, Order order) {
        OrderProduct orderProduct = OrderProduct.builder()
                .product(product)
                .order(order)
                .orderProductQuantity(orderProductDto.getOrderProductQuantity())
                .price(orderProductDto.getPrice())
                .build();
        return orderProduct;

    }

    public int getTotalPrice() {
        return price*orderProductQuantity;
    }*/
}
    //상품정보


