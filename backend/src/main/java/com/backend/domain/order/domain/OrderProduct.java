package com.backend.domain.order.domain;

import com.backend.domain.order.dto.OrderProductDto;
import com.backend.domain.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    //1
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    //    private Long memberId;


    private Integer productQuantity;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;




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


