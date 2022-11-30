package com.backend.domain.order.application;

import com.backend.domain.order.dao.OrderProductRepository;
import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.dto.CartOrderDto;
import com.backend.domain.order.dto.CartOrderProductDto;
import com.backend.domain.order.dto.OrderDto;
import com.backend.domain.order.dto.OrderHistoryDto;
import com.backend.domain.order.exception.OrderNotFound;
import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;
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

import static com.backend.domain.order.domain.OrderStatus.SHIPPED;
import static com.backend.domain.order.domain.OrderStatus.SHIPPING;
import static com.backend.global.error.ExceptionCode.CANNOT_CHANGE_ORDER;

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
        log.info("Service/ 주문생성을 위한 상품찾기 시작");
        Product product = productRepository.findById(orderDto.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        log.info("Service/ 주문생성을 위한 유저찾기 시작");
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        log.info("Service/ 주문상품리스트 생성 시작");
        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderDto.getQuantity());
        orderProductList.add(orderProduct);
        log.info("Service/ 주문 생성 시작");
        Order order = Order.createOrder(user, orderProductList, orderDto);

        orderRepository.save(order);

        return order;
    } //주문 토탈가격도 보내기
    //주문 여러건 한번에 받기


    public Order orders(CartOrderDto cartOrderDto, Long userId) {

        List<CartOrderProductDto> cartOrderProductDtoList = cartOrderDto.getCartOrderProductDtoList();
        List<OrderProduct> orderProductList = new ArrayList<>();
        log.info("Service/ 장바구니 주문 생성을 위한 유저찾기 시작");
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        log.info("Service/ 장바구니 주문상품리스트 생성 시작");
        for (CartOrderProductDto cartOrderProductDto : cartOrderProductDtoList) {
            Product product = productRepository.findById(cartOrderProductDto.getProductId())
                    .orElseThrow(EntityNotFoundException::new);

            OrderProduct orderProduct = OrderProduct.createOrderProduct(product, cartOrderProductDto.getQuantity());
            orderProductList.add(orderProduct);
        }
        log.info("Service/ 장바구니 주문 생성 시작");
        Order order = Order.createCartOrder(user, orderProductList, cartOrderDto);
        orderRepository.save(order);



        return order;
    }
    @Transactional
    public Order update(Long orderId, Order order) {
        Order findorder = orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);

        if(order.isShipping()) {
            log.info("Service/ 주문번호 : " + orderId + " 주문정보변경불가능");
            throw new BusinessLogicException(CANNOT_CHANGE_ORDER);

        }
        log.info("Service/ 주문정보 받는사람 이름 수정");
        Optional.ofNullable(order.getReceiverName())
                .ifPresent(findorder::setReceiverName);
        log.info("Service/ 주문정보 받는사람 번호 수정");
        Optional.ofNullable(order.getReceiverPhone())
                .ifPresent(findorder::setReceiverPhone);
        log.info("Service/ 주문정보 받는사람 주소 수정");
        Optional.ofNullable(order.getReceiverAddress())
                .ifPresent(findorder::setReceiverAddress);


        return orderRepository.save(findorder);
    }

    @Transactional
    //판매자 전용 배송중으로 변경하는 기능
    public Order updateStatus(Long orderId) {
        Order findOrder = orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);
        log.info("Service/ 주문번호 : " + orderId + " 주문상태 배송중으로 변경");

        findOrder.setOrderStatus(SHIPPING);
        orderRepository.save(findOrder);

        return findOrder;
    }

    public Page<OrderHistoryDto> getAllList(Pageable pageable) {
        log.info("Service/ 모든 주문내역 조회 시작");
        List<Order> orders = orderRepository.findAll();
        Long totalQuantity = orderRepository.countAllOrder();


        return new PageImpl<OrderHistoryDto>(OrderHistoryDto.from(orders), pageable, totalQuantity);
    }


    public Page<OrderHistoryDto> getOrderList(Long userId, Pageable pageable) {
        log.info("Service/ userId : " + userId + "주문내역 조회 시작");
        List<Order> orders = orderRepository.findOrders(userId, pageable);
        Long totalQuantity = orderRepository.countOrder(userId);

        return new PageImpl<OrderHistoryDto>(OrderHistoryDto.from(orders), pageable, totalQuantity);
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        log.info("Service/ 주문번호 : " + orderId + " 주문취소");
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        if(order.isShipping()) {
            log.info("Service/ 주문번호 : " + orderId + " 주문취소불가능");
            throw new BusinessLogicException(ExceptionCode.CANNOT_CANCEL_ORDER);

        }
        orderRepository.delete(order);
    }

    //자동 호출
    @Transactional
    public void autoUpdate() {
        log.info("Service/ 배송중상태의 주문들 모두 배송완료 상태로 변경");
        List<Order> orders = orderRepository.findByOrderStatus(SHIPPING);
        for (Order order : orders) {
            order.setOrderStatus(SHIPPED);
            //orderRepository.save(order);

        }


    }
    //판매량 조회
    public int getSalesRate(long productId) {
        log.info("Service/ 상품별 주문내역 조회 시작");
        List<OrderProduct>  orderProductList = orderProductRepository.findByProductProductId(productId);
        log.info("Service/ 주문상품들의 총 가격계산 시작");
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
