package com.backend.domain.order.application;

import com.backend.domain.order.dao.OrderProductRepository;
import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.domain.OrderStatus;
import com.backend.domain.order.domain.QOrderProduct;
import com.backend.domain.order.dto.*;
import com.backend.domain.order.exception.OrderNotFound;
import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.exception.ProductNotFound;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.backend.domain.order.domain.OrderStatus.SHIPPED;
import static com.backend.domain.order.domain.OrderStatus.SHIPPING;
import static com.backend.domain.order.domain.QOrder.order;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
    public class OrderService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    @Transactional
    public Order order(OrderDto orderDto, Long userId) {
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        log.info("");
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);

        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getQuantity());
        orderProductList.add(orderProduct);

        Order order = Order.createOrder(user, orderProductList, orderDto);
        orderRepository.save(order);

        return order;
    } //주문 토탈가격도 보내기
    //주문 여러건 한번에 받기


    public Order orders(CartOrderDto cartOrderDto, Long userId) {

        List<CartOrderProductDto> cartOrderProductDtoList = cartOrderDto.getCartOrderProductDtoList();
        List<OrderProduct> orderProductList = new ArrayList<>();

        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);

        for (CartOrderProductDto cartOrderProductDto : cartOrderProductDtoList) {
            Product product = productRepository.findById(cartOrderProductDto.getProductId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, cartOrderProductDto.getQuantity());
            orderProductList.add(orderProduct);
        }
        Order order = Order.createCartOrder(user, orderProductList, cartOrderDto);
        orderRepository.save(order);



        return order;
    }
    @Transactional
    public Order update(Long orderId, Order order) {
        Order findorder = orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);

        Optional.ofNullable(order.getReceiverName())
                .ifPresent(findorder::setReceiverName);
        Optional.ofNullable(order.getReceiverPhone())
                .ifPresent(findorder::setReceiverPhone);
        Optional.ofNullable(order.getReceiverAddress())
                .ifPresent(findorder::setReceiverAddress);


        return orderRepository.save(findorder);
    }

    @Transactional
    //판매자 전용 배송중으로 변경하는 기능
    public Order updateStatus(Long orderId) {
        Order findOrder = orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);
        findOrder.setOrderStatus(SHIPPING);
        orderRepository.save(findOrder);

        return findOrder;
    }

    public Page<OrderHistoryDto> getAllList(Pageable pageable) {
        List<Order> orders = orderRepository.findAll();
        Long totalQuantity = orderRepository.countAllOrder();


        return new PageImpl<OrderHistoryDto>(OrderHistoryDto.from(orders), pageable, totalQuantity);
    }


    public Page<OrderHistoryDto> getOrderList(Long userId, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(userId, pageable);
        Long totalQuantity = orderRepository.countOrder(userId);

        return new PageImpl<OrderHistoryDto>(OrderHistoryDto.from(orders), pageable, totalQuantity);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
        orderRepository.delete(order);
    }

    //자동 호출
    @Transactional
    public void autoUpdate() {
        List<Order> orders = orderRepository.findByOrderStatus(SHIPPING);
        for (Order order : orders) {
            order.setOrderStatus(SHIPPED);
            //orderRepository.save(order);

        }


    }
    //판매량 조회
    public int getSalesRate(long productId) {
        List<OrderProduct>  orderProductList = orderProductRepository.findByProductProductId(productId);
        int totalQuantity = 0;
        for(OrderProduct orderProduct : orderProductList) {
            totalQuantity += orderProduct.getQuantity();
        }
        return totalQuantity;
    }



}


    /* public Long orders(List<OrderDto> orderDtoList, String email){

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(MemberNotFound::new);
        List<OrderProduct> orderProductList = new ArrayList<>();

        for (OrderDto orderDto : orderDtoList) {
            Product product = productRepository.findById(orderDto.getProductId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getQuantity());
            orderProductList.add(orderProduct);
        }

        Order order = Order.createOrder(user, orderProductList, OrderDto.createOrderDto());
        orderRepository.save(order);

        return order.getOrderId();

    }

    public Page<Order> findUserOrders(long userId,int page, int size){
        return orderRepository.findOrders(userId, PageRequest.of(page,size, Sort.by("orderId").descending()));

    }*/


