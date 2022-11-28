package com.backend.domain.order.api;

import com.backend.domain.order.application.OrderService;
import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.dto.*;

import com.backend.domain.order.mapper.OrderMapper;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.dto.ProductPatchDto;


import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;

import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;

    private final OrderRepository orderRepository;

    //주문
    @PostMapping("/orders")
    public ResponseEntity order(@CurrentUser CustomUserDetails authUser, @RequestBody @Valid OrderDto orderDto) {
        log.info("controller/ 주문 post를 위한 UserId 가져오기");
        Long userId = authUser.getUser().getUserId();
        Long orderId;
        log.info("controller/ 주문 post 시작");
        Order order = orderService.order(orderDto, userId);

        return new ResponseEntity<>(new OrderPriceDto(order), HttpStatus.CREATED);
    }

    @PostMapping("/orders/cart")
    public ResponseEntity cartOrders(@CurrentUser CustomUserDetails authUser, @RequestBody @Valid CartOrderDto cartOrderDto) {
        log.info("controller/ 주문들 post를 위한 UserId 가져오기");
        Long userId = authUser.getUser().getUserId();
        log.info("controller/ 주문들 post 시작");
        Order order = orderService.orders(cartOrderDto, userId);

        return new ResponseEntity<>(new OrderPriceDto(order), HttpStatus.CREATED);
    }


    //주문지 수정
    @PatchMapping("/orders/{orderId}")
    public ResponseEntity update(@PathVariable Long orderId,
                                 @Valid @RequestBody OrderPatchDto orderPatchDto) {
        Order order = mapper.orderPatchDtoToOrder(orderPatchDto);
        log.info("controller/ 주문 patch 시작");
        Order response = orderService.update(orderId, order);

        return ResponseEntity.ok(new SingleResponseDto<>(mapper.orderToResponseDto(response)));
    }


    //주문 취소
    @DeleteMapping("orders/{order-id}")
    public ResponseEntity cancel(@PathVariable("order-id") @Positive long orderId) {
        log.info("controller/ 주문 cancel 시작");
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = {"/orders", "/orders/{page}"})
    public ResponseEntity<MultiResponse> getList(@PathVariable("page") Optional<Integer> page, @CurrentUser CustomUserDetails authUser) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 15);
        log.info("controller/ 유저별 주문 내역 get 시작");
        Page<OrderHistoryDto> ordersHistoryDtoList = orderService.getOrderList(authUser.getUser().getUserId(), pageable);
        List<OrderHistoryDto> content = ordersHistoryDtoList.getContent();


        return new ResponseEntity<>(new MultiResponse<>(content, ordersHistoryDtoList), HttpStatus.OK);
    }

    @GetMapping(value = {"/orders/all", "/orders/all/{page}"})
    public ResponseEntity<MultiResponse> getAllList(@PathVariable("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 15);
        log.info("controller/ 모든 주문 내역 get 시작");
        Page<OrderHistoryDto> ordersHistoryDtoList = orderService.getAllList(pageable);
        List<OrderHistoryDto> content = ordersHistoryDtoList.getContent();


        return new ResponseEntity<>(new MultiResponse<>(content, ordersHistoryDtoList), HttpStatus.OK);
    }


//판매자 전용기능 필요?없음. 버튼누르면 그냥  order상태변경해서 저장하기만 하면됌. 응답으로는 patch리스폰스랑 같이
    @PatchMapping("/orders/status/{order-id}")
    public ResponseEntity updateStatus(@PathVariable("order-id") long orderId) {
        log.info("controller/ 주문 상태 patch 시작");
        Order response = orderService.updateStatus(orderId);
        return ResponseEntity.ok(new SingleResponseDto<>(mapper.orderToResponseDto(response)));

    }

    @GetMapping("/orders/product/{product-id}")
    public ResponseEntity getSalesRate(@PathVariable("product-id") long productId) {
        log.info("controller/ 상품별 판매량 get 시작");
        int orderTotalQuantity = orderService.getSalesRate(productId);
        return ResponseEntity.ok(new SingleResponseDto<>(orderTotalQuantity));
    }
    /*@PostMapping(value = "/cart/orders")
    public @ResponseBody ResponseEntity cartOrder(@RequestBody CartOrderDto cartOrderDto, @CurrentUser CustomUserDetails authUser){

        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();


        Long orderId = orderService.cartOrder(cartOrderDtoList, principal.getName());
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);*/
    }





