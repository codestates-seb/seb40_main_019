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
import com.backend.domain.user.domain.AuthUser;
import com.backend.global.annotation.CurrentMember;
import com.backend.global.dto.MultiResponseDto;
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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;

    private final OrderRepository orderRepository;

    //주문
    @PostMapping
    public ResponseEntity order(@CurrentMember AuthUser authuser, @RequestBody @Valid OrderDto orderDto) {
        Long userId = authuser.getUserId();
        Long orderId;
        orderId = orderService.order(orderDto, userId);
        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }


    //주문지 수정
    @PatchMapping("{orderId}")
    public ResponseEntity update(@PathVariable Long orderId,
                                 @Valid @RequestBody OrderPatchDto orderPatchDto) {
        Order order = mapper.orderPatchDtoToOrder(orderPatchDto);
        Order response = orderService.update(orderId, order);

        return ResponseEntity.ok(new SingleResponseDto<>(mapper.orderToResponseDto(response)));
    }


    //주문 취소
    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping({"/{page}"})
    public ResponseEntity<MultiResponseDto> getOrders(@PathVariable("page") Optional<Integer> page, @CurrentMember AuthUser authUser) {
        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 15);

        Page<OrderHistoryDto> ordersHistoryDtoList = orderService.getOrderList(authUser.getUserId(), pageable);
        List<OrderHistoryDto> content = ordersHistoryDtoList.getContent();


        return new ResponseEntity<>(new MultiResponseDto<>(content, ordersHistoryDtoList), HttpStatus.OK);
    }
}
/* @GetMapping("/{user-id}")
     public ResponseEntity getUserOrders(@PathVariable("user-id") long userId,
                                       @RequestParam int page,
                                       @RequestParam int size, @CurrentMember AuthUser authuser){
         userId = authuser.getUserId();
         Page<Order> userOrders = orderService.findUserOrders(userId, page - 1, size);
         List<Order> content = userOrders.getContent();


         return new ResponseEntity<>(new MultiResponseDto<>(mapper.toDtos(content),userOrders), HttpStatus.OK);
     }*/



    //주문생성
