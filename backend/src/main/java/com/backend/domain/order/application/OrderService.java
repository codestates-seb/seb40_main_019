package com.backend.domain.order.application;

import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.dto.OrderDto;
import com.backend.domain.order.dto.OrderHistoryDto;
import com.backend.domain.order.dto.OrderProductDto;
import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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



    public Long order(OrderDto orderDto, String email){
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(MemberNotFound::new);

        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getQuantity());
        orderProductList.add(orderProduct);

        Order order = Order.createOrder(user, orderProductList);
        orderRepository.save(order);

        return order.getOrderId();
    }

    @Transactional
    public Page<OrderHistoryDto> getOrderList(String email, Pageable pageable) {

        List<Order> orders  = orderRepository.findOrders(email, pageable);
        Long totalQuantity = orderRepository.countOrder(email);

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
    }

    public Long orders(List<OrderDto> orderDtoList, String email){

        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(MemberNotFound::new);
        List<OrderProduct> orderProductList = new ArrayList<>();

        for (OrderDto orderDto : orderDtoList) {
            Product product = productRepository.findById(orderDto.getProductId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getQuantity());
            orderProductList.add(orderProduct);
        }

        Order order = Order.createOrder(user, orderProductList);
        orderRepository.save(order);

        return order.getOrderId();
    }
}
