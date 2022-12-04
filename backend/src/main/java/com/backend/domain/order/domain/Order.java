package com.backend.domain.order.domain;

import com.backend.domain.order.dto.CartOrderDto;
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

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int orderTotalPrice;

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
        //공부해서 리펙토링 필요
        order.setOrderTotalPrice(order.getTotalPrice());
        return order;
    }

    public static Order createCartOrder(User user, List<OrderProduct> orderProductList, CartOrderDto cartOrderDto) {
        Order order = new Order();
        order.setUser(user);
        order.setReceiverAddress(cartOrderDto.getReceiverAddress());
        order.setReceiverName(cartOrderDto.getReceiverName());
        order.setReceiverPhone(cartOrderDto.getReceiverPhone());
        order.setZipCode(cartOrderDto.getReceiverZipcode());

        for (OrderProduct orderProduct : orderProductList) {
            order.addOrderProduct(orderProduct);
        }
        order.setOrderStatus(OrderStatus.PROCESS);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderProducts(orderProductList);
        //공부해서 리펙토링 필요
        order.setOrderTotalPrice(order.getTotalPrice());
        return order;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for(OrderProduct orderProduct : orderProducts){
            totalPrice += orderProduct.getTotalPrice();
        }
        return totalPrice;
    }

    public boolean isShipping() {
        return orderStatus.getStatusNumber()>=2;
    }



}
