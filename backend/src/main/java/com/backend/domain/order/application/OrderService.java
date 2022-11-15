package com.backend.domain.order.application;

import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderStatus;
import com.backend.domain.order.dto.OrderRequestDto;
import com.backend.domain.order.dto.OrderResponseDto;
import com.backend.domain.order.dto.ResponseDto;
import com.backend.domain.order.exception.OrderNotFound;
import com.backend.domain.order.mapper.OrderMapper;
import com.backend.domain.point.application.PointService;
import com.backend.domain.point.dao.PointRepository;
import com.backend.domain.product.application.ProductService;
import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.user.application.UserService;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.AuthUser;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.global.annotation.CurrentMember;
import com.backend.global.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.backend.domain.order.domain.OrderStatus.COMP;
import static com.backend.domain.order.domain.OrderStatus.ORDER;

@Transactional
@Slf4j
@Service
@RequiredArgsConstructor
    public class OrderService {
        private final OrderRepository orderRepository;
        private final OrderMapper orderMapper;

        private final UserRepository userRepository;
        private final ProductRepository productRepository;





    @Transactional
        //주문생성저장
        public Order createOrder(Long userId, Order order){
//        Order order = Order.createOrder(memberId, reqOrder.getOrderProducts(),reqOrder.getBuyerAddress());
            //order.getUser().setUserId(userId);
            User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
            order.setUser(user);
            order.setTotalPrice(order.getTotalPrice());
            order.addOrderProducts(order.getOrderProducts());

            order.setOrderStatus(ORDER);
            return orderRepository.save(order);

        }

    @Transactional
    //주문 재고 빼고
    public ResponseDto placeOrder(Long userId, OrderRequestDto OrderDto){

        Order order = orderMapper.toOrderEntity(OrderDto);
        Order createOrder = createOrder(userId,order);

        //createOrder.getOrderProducts().forEach(pd -> productService.decreaseStock(pd.getProductId(), pd.getProductQuantity()));  //상품재고 감소
        OrderResponseDto orderResponseDto = orderMapper.toOrderResponseDto(order);
        return new ResponseDto(orderResponseDto);
    }
    @Transactional

    //쿼리로직 배낀거
    public List<Order> getOrderByQueryDSL(Long userId) {
        return orderRepository.searchOrder(userId);
    }

    @Transactional
    //유저아이디로 조회
    public ResponseDto getOrderInfo(User user) {
        List<Order> order = getOrderByQueryDSL(user.getUserId());
        List<OrderResponseDto> orderResponseDto = orderMapper.toOrderResponseDtoList(order);
        return new ResponseDto(orderResponseDto);
    }

    //주문지 변경
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
    }

    @Transactional
    public void delete(Long userId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFound::new);
        orderRepository.delete(order);
    }


    /*@Transactional
    public Order create(long userId, Order order) {
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        order.setUser(user);
        order.setTotalPrice();
        order.addOrderProducts(order.getOrderProducts());
        order.setOrderStatus(OrderStatus.ORDER);
        //order.getOrderProducts().forEach(pd -> productService.decreaseStock(pd.getOrderProductId(), pd.getOrderProductQuantity())); //프로덕트쪽에 재고관리서비스만들어야함
        return orderRepository.save(order);
    }
   //주문 생성
    /*@Transactional
    public Order create(long userId, Order order) {
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        order.setUser(user);
        order.setTotalPrice();
        order.addOrderProducts(order.getOrderProducts());
        order.setOrderStatus(OrderStatus.ORDER);
        //order.getOrderProducts().forEach(pd -> productService.decreaseStock(pd.getOrderProductId(), pd.getOrderProductQuantity())); //프로덕트쪽에 재고관리서비스만들어야함
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
    //포인트로만 결제
    //@Transactional
    /*public void payOnlyPoint(Order order) {
        User user = order.getUser();
        long point = user.getPoint().getCash();
        int payPrice = order.getTotalPrice();

        if(payPrice > point) {
            throw new RuntimeException("포인트가 부족합니다");
            //결제창으로 넘어가게 해야함
        }
        order.setOrderStatus(COMP);
        orderRepository.save(order);*/
    }



