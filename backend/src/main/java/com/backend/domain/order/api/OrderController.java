package com.backend.domain.order.api;

import com.backend.domain.order.application.OrderService;
import com.backend.domain.order.dto.OrderPostDto;
import com.backend.domain.point.application.PointService;
import com.backend.domain.user.application.UserService;
import com.backend.global.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final PointService pointService;
    private final UserService userService;

    @PostMapping("/orders")
    public ResponseEntity create(@Valid @RequestBody OrderPostDto orderPostDto) {

        long userId = user.getUserId;
        return ResponseEntity.ok(orderService.create(userId, orderPostDto));
    }

    @GetMapping("/orders/user/{/user-id}")

}
