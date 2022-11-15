package com.backend.domain.order.api;

import com.backend.domain.order.application.OrderService;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.dto.OrderPatchDto;
import com.backend.domain.order.dto.OrderPostDto;
import com.backend.domain.order.dto.OrderResponseDto;
import com.backend.domain.order.mapper.OrderMapper;
import com.backend.domain.point.application.PointService;
import com.backend.domain.user.application.UserService;
import com.backend.domain.user.domain.AuthUser;
import com.backend.global.annotation.CurrentMember;
import com.backend.global.dto.MultiResponseDto;
import com.backend.global.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    private final OrderMapper mapper;

    @PostMapping("/orders")
    public ResponseEntity create(@CurrentMember AuthUser authUser,
            @Valid @RequestBody OrderPostDto orderPostDto) {

        long userId = authUser.getUserId();
        Order order = mapper.orderPostDtoToOrder(orderPostDto);
        Order Response = orderService.create(userId, order);
        OrderResponseDto orderResponseDto = mapper.orderToOrderResponseDto(order);
        return new ResponseEntity<>(new SingleResponseDto<>(orderResponseDto), HttpStatus.CREATED);//created로 바꿔야함
    }

    @PatchMapping("/orders/{order-id}")
    public ResponseEntity update(@CurrentMember AuthUser authUser, @PathVariable("order-id") Long orderId, @Valid OrderPatchDto orderPatchDto) {
        orderPatchDto.setOrderId(orderId);
        Order order = mapper.orderPatchDtoToOrder(orderPatchDto);
        Order response = orderService.update(orderId, order);
        long userId = authUser.getUserId();;
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.orderToOrderResponseDto(response)), HttpStatus.CREATED);

    }

    @DeleteMapping("/order/{order-id}")
    public ResponseEntity delete(@CurrentMember AuthUser authUser, @PathVariable("order-id") Long orderId) {
        Long userId = authUser.getUserId();
        orderService.delete(userId, orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


   /* @GetMapping("/orders/product/{product-id}")
    public ResponseEntity<MultiResponseDto> getOrdersByProduct(@PathVariable("product-id"), @Positive @RequestParam int page,
                                                               @Positive @RequestParam int size) {
        Page<Order> pageOrders = orderService.findOrdersByProduct(page -1, size);
        List<Order> orders = pageOrders.getContent();

        return ResponseEntity.ok(orderService.findOrdersByProduct(page -1, size));
    }*/

   @GetMapping("/orders/user/{user-id}")
    public ResponseEntity findOrdersByUser(@PathVariable("user-id") Long userId, @Positive @RequestParam int page,
                                           @Positive @RequestParam int size) {
        Page<Order> pageOrders = orderService.findOrdersByUser(userId, page -1, size);
        List<Order> orders = pageOrders.getContent();



        return new ResponseEntity<>(
               new MultiResponseDto<>(mapper.ordersToOrderResponseDtos(orders), pageOrders),
               HttpStatus.OK);

    }



}
