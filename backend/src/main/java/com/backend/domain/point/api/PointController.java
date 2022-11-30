package com.backend.domain.point.api;

import com.backend.domain.order.dao.OrderProductRepository;
import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.point.application.PointService;
import com.backend.domain.point.domain.PointType;
import com.backend.domain.point.dto.PointChargeDto;
import com.backend.domain.point.dto.PointResponseDto;
import com.backend.domain.point.mapper.PointMapper;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.dto.Response.MultiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/point")
public class PointController {

    private final UserRepository userRepository;
    private final OrderProductRepository orderProductRepository;
    private final PointService pointService;
    private final PointMapper mapper;
    private final OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Integer> charge(@CurrentUser CustomUserDetails authUser, @RequestBody PointChargeDto pointChargeDto) {
        Long userId = authUser.getUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        int price = pointChargeDto.getPrice();
        PointType pointType = pointChargeDto.getPointType();
        int newRestCash = pointService.addCash(user, price, pointType);
        return new ResponseEntity<Integer>(newRestCash, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity find(@CurrentUser CustomUserDetails authUser) {
        User user = userRepository.findById(authUser.getUser().getUserId()).orElseThrow(MemberNotFound::new);
        int restCash = pointService.getRestCash(user);

        return new ResponseEntity<>(restCash, HttpStatus.OK);
    }

    @PostMapping("/{order-id}")
    public ResponseEntity pay(@CurrentUser CustomUserDetails authUser, @PathVariable("order-id") @Positive long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        pointService.pay(order, authUser.getUser().getUserId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<MultiResponse> getList(@RequestParam int page, @CurrentUser CustomUserDetails authUser) {
        Pageable pageable = PageRequest.of(page - 1, 15);
        log.info("controller/ 유저별 포인트내역 조회 시작");
        Page<PointResponseDto> pointResponseDtoList = pointService.getPointList(authUser.getUser().getUserId(), pageable);
        List<PointResponseDto> content = pointResponseDtoList.getContent();


        return new ResponseEntity<>(new MultiResponse<>(content, pointResponseDtoList), HttpStatus.OK);
    }


}
