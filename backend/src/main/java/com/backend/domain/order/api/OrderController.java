package com.backend.domain.order.api;

import com.backend.domain.order.application.OrderService;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.dto.OrderDto;
import com.backend.domain.order.dto.OrderPatchDto;
import com.backend.domain.order.dto.OrderRequestDto;
import com.backend.domain.order.mapper.OrderMapper;
import com.backend.domain.user.domain.AuthUser;
import com.backend.global.annotation.CurrentMember;
import com.backend.global.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public ResponseEntity order(@CurrentMember AuthUser authUser, @RequestBody @Valid OrderDto orderDto)


    //주문생성
