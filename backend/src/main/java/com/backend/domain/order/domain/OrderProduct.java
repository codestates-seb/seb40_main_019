package com.backend.domain.order.domain;

import com.backend.domain.order.dto.OrderProductDto;
import com.backend.domain.product.domain.Product;
import com.backend.global.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor

public class OrderProduct extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    //1
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int price;

    private int quantity;

    private OrderProductReviewStatus reviewStatus;
    public static OrderProduct createOrderProduct(Product product, int quantity){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);
        orderProduct.setPrice(product.getPrice());
        orderProduct.setReviewStatus(OrderProductReviewStatus.WRITING);

       // product.removeStock(quantity);
        return orderProduct;
    }

    public int getTotalPrice(){
        return price*quantity;
    }

    public void cancel() {
        //this.getProduct().addStock(quantity);//todo:재고추가로직만들기
    }
    }





