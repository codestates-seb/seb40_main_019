package com.backend.domain.order.domain;

import com.backend.domain.order.dto.OrderDto;
import com.backend.domain.user.domain.User;
import com.backend.global.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String receiverAddress;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhone;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void addOrderProduct(OrderProduct orderProduct){
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public static Order createOrder(User user, List<OrderProduct> orderProductList, OrderDto orderDto){
        Order order = new Order();
        order.setUser(user);
        order.setReceiverAddress(orderDto.getReceiverAddress());
        order.setReceiverName(orderDto.getReceiverName());
        order.setReceiverPhone(orderDto.getReceiverPhone());
        order.setZipCode(orderDto.getReceiverZipcode());

        for(OrderProduct orderProduct : orderProductList){
            order.addOrderProduct(orderProduct);
        }
        order.setOrderStatus(OrderStatus.PROCESS);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderProducts(orderProductList);
        return order;
    }


    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderProduct orderProduct : orderProducts){
            totalPrice += orderProduct.getTotalPrice();
        }
        return totalPrice;
    }

    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCEL;

        for(OrderProduct orderProduct : orderProducts){
            orderProduct.cancel();
        }
    }

}