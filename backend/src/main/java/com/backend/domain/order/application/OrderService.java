package com.backend.domain.order.application;

import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.dto.OrderPostDto;
import com.backend.domain.point.application.PointService;
import com.backend.domain.product.application.ProductService;
import com.backend.domain.user.application.UserService;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public Order create(long userId, OrderPostDto orderPostDto) {
        User user = userRepository.findByUserId(userId);
        Order order = Order.createOrder(orderPostDto, user);
        return orderRepository.save(order);
    }
}
