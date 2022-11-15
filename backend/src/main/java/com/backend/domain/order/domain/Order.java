package com.backend.domain.order.domain;

import com.backend.domain.user.domain.User;
import com.backend.global.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String receiverAddress;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhone;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private int totalPrice;

    public void setUser(User user) {
        this.user = user;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public void setTotalPrice() {
        this.totalPrice = getTotalPrice();
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    //총주문금액
    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderProduct orderProduct : orderProducts) {
            totalPrice+= orderProduct.getTotalPrice();
        }

        return totalPrice;

    }

    public void addOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
        orderProducts.forEach(od -> {od.setOrder(this);});
    }



}


    /*public void addOrderProduct(OrderProduct orderProduct) {
                        orderProducts.add(orderProduct);
                        orderProduct.setOrder(this);
                    }*/
    /*@Builder
    public Order(Long orderId, String zipCode, String receiverAddress,
                 String receiverName, String receiverPhone,User user, OrderStatus orderStatus){
        this.orderId = orderId;
        this.zipCode = zipCode;
        this.receiverAddress = receiverAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.user = user;
        this.orderStatus = orderStatus;
        //매퍼를 사용하면 디티오와 엔티티가 서로 연관된 필드가있지만 필요없는 필드도 있는데 그걸 전부 변환해준다.. 하지만 빌더를 사용하면 원하는 필더만
        //생성자로 가져올수 있다? 이게 맞나? 백엔드분들에게 빌프패턴 검사받기
    }

    public static Order createOrder(OrderPostDto orderPostDto, User user) {
        Order order = Order.builder()
                .user(user)
                .receiverAddress(orderPostDto.getReceiverAddress())
                .receiverName(orderPostDto.getReceiverName())
                .receiverPhone(orderPostDto.getReceiverPhone())
                .build();

        return order;



    }

    public void patchOrder(OrderPatchDto orderPatchDto, User user) {
        String updatedReceiverName = orderPatchDto.getReceiverName();
        String updatedReceiverPhone = orderPatchDto.getReceiverPhone();
        String updatedReceiverAddress = orderPatchDto.getReceiverAddress();

        this.receiverName = updatedReceiverName == null? this.receiverName : updatedReceiverName;
        this.receiverPhone = updatedReceiverPhone == null? this.receiverPhone : updatedReceiverPhone;
        this.receiverAddress = updatedReceiverAddress == null? this.receiverAddress : updatedReceiverName;
    }*/
    // todo : orderStatus 에 따라 주문 상태 변경 가능 유무


