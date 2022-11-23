package com.backend.domain.order.api;

import com.backend.domain.order.application.OrderService;
import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.dto.OrderDto;
import com.backend.domain.order.dto.OrderHistoryDto;
import com.backend.domain.order.dto.OrderPatchDto;

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
        Long userId = authUser.getUser().getUserId();
        Long orderId;
        orderId = orderService.order(orderDto, userId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }


    //주문지 수정
    @PatchMapping("/orders/{orderId}")
    public ResponseEntity update(@PathVariable Long orderId,
                                 @Valid @RequestBody OrderPatchDto orderPatchDto) {
        Order order = mapper.orderPatchDtoToOrder(orderPatchDto);
        Order response = orderService.update(orderId, order);

        return ResponseEntity.ok(new SingleResponseDto<>(mapper.orderToResponseDto(response)));
    }


    //주문 취소
    @DeleteMapping("orders/{order-id}")
    public ResponseEntity cancel(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = {"/orders", "/orders/{page}"})
    public ResponseEntity<MultiResponse> getList(@PathVariable("page") Optional<Integer> page, @CurrentUser CustomUserDetails authUser) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 15);

        Page<OrderHistoryDto> ordersHistoryDtoList = orderService.getOrderList(authUser.getUser().getUserId(), pageable);
        List<OrderHistoryDto> content = ordersHistoryDtoList.getContent();


        return new ResponseEntity<>(new MultiResponse<>(content, ordersHistoryDtoList), HttpStatus.OK);
    }

    @GetMapping(value = {"/orders/all", "/orders/all/{page}"})
    public ResponseEntity<MultiResponse> getAllList(@PathVariable("page") Optional<Integer> page) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 15);

        Page<OrderHistoryDto> ordersHistoryDtoList = orderService.getAllList(pageable);
        List<OrderHistoryDto> content = ordersHistoryDtoList.getContent();


        return new ResponseEntity<>(new MultiResponse<>(content, ordersHistoryDtoList), HttpStatus.OK);
    }


//판매자 전용기능 필요?없음. 버튼누르면 그냥  order상태변경해서 저장하기만 하면됌. 응답으로는 patch리스폰스랑 같이
    @PatchMapping("/orders/status/{order-id}")
    public ResponseEntity updateStatus(@PathVariable("order-id") long orderId) {
        Order response = orderService.updateStatus(orderId);
        return ResponseEntity.ok(new SingleResponseDto<>(mapper.orderToResponseDto(response)));

    }


}

