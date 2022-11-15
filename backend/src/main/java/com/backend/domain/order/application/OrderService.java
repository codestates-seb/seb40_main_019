package com.backend.domain.order.application;

import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderStatus;
import com.backend.domain.order.exception.OrderNotFound;
import com.backend.domain.point.application.PointService;
import com.backend.domain.product.application.ProductService;
import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.user.application.UserService;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final PointService pointService;
    private final UserService userService;
    private final ProductService productService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    //주문 생성
    @Transactional
    public Order create(long userId, Order order) {
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        order.setUser(user);
        order.setTotalPrice();
        order.addOrderProducts(order.getOrderProducts());
        order.setOrderStatus(OrderStatus.ORDER);
        return orderRepository.save(order);
    }
    //한번 물어보자,,

    @Transactional
    public Order update(Long userId, Order order) {
        Order findorder = orderRepository.findById(order.getOrderId()).orElseThrow(OrderNotFound::new);
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        Optional.ofNullable(order.getReceiverAddress())
                .ifPresent(receiverAddress -> findorder.setReceiverAddress(receiverAddress));
        Optional.ofNullable(order.getReceiverName())
                .ifPresent(receiverName -> findorder.setReceiverName(receiverName));
        Optional.ofNullable(order.getReceiverPhone())
                .ifPresent(receiverPhone -> findorder.setReceiverAddress(receiverPhone));
        return orderRepository.save(findorder);
    }// 주문상태가 배송중이상이면 주문수정 불가하개해야함

    @Transactional
    public void delete(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);
        orderRepository.delete(order);
    }

    @Transactional
    public Page<Order> findOrdersByUser(long userId, int page, int size) {
       Page<Order> orders = orderRepository.findByUser(userId, PageRequest.of(page,size, Sort.by("orderId").descending()));
       return orders;
    }


}
