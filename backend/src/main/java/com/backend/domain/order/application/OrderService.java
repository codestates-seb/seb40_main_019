package com.backend.domain.order.application;

import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.dto.OrderDto;
import com.backend.domain.order.dto.OrderHistoryDto;
import com.backend.domain.order.dto.OrderProductDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
    public class OrderService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;



    public Long order(OrderDto orderDto, Long userId){
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);

        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getQuantity());
        orderProductList.add(orderProduct);

        Order order = Order.createOrder(user, orderProductList, orderDto);
        orderRepository.save(order);

        return order.getOrderId();
    }

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;
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
    public Page<OrderHistoryDto> getOrderList(Long userId, Pageable pageable) {

        List<Order> orders = orderRepository.findOrders(userId, pageable);
        Long totalQuantity = orderRepository.countOrder(userId);

        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            List<OrderProduct> orderProducts = order.getOrderProducts();
            for (OrderProduct orderProduct : orderProducts) {
                // ProductImg productImg = productImgRepository.findByItemIdAndRepimgYn(orderProductt.getIte().getId(), "Y");
                OrderProductDto orderProductDto = new OrderProductDto(orderProduct);
                orderHistoryDto.addOrderProductDto(orderProductDto);
            }

            orderHistoryDtos.add(orderHistoryDto);
        }

        return new PageImpl<OrderHistoryDto>(orderHistoryDtos, pageable, totalQuantity);
    }

    public void cancelOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
        orderRepository.delete(order);
    }

    //todo: 판매자가 수동적으로 주문상태변경가능케하기

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
}
