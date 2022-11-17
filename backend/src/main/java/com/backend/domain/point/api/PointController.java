package com.backend.domain.point.api;

import com.backend.domain.order.dao.OrderProductRepository;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.point.application.PointService;
import com.backend.domain.point.domain.Point;
import com.backend.domain.point.dto.PointChargeDto;
import com.backend.domain.point.dto.PointResponseDto;
import com.backend.domain.point.mapper.PointMapper;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.AuthUser;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.global.annotation.CurrentMember;
import com.backend.global.dto.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/point")
public class PointController {

    private final UserRepository userRepository;
    private final OrderProductRepository orderProductRepository;
    private final PointService pointService;
    private final PointMapper mapper;

    @PostMapping
    public ResponseEntity charge(@CurrentMember AuthUser authUser, @RequestBody PointChargeDto pointChargeDto){
        Long userId =authUser.getUserId();
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        Point point = pointService.addCash(user, pointChargeDto);


        return new ResponseEntity<>(new SingleResponseDto<>(mapper.toResponseDto(point)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity find(@CurrentMember AuthUser authUser, @RequestBody PointChargeDto pointChargeDto) {
        User user = userRepository.findById(authUser.getUserId()).orElseThrow(MemberNotFound::new);
        Point point = pointService.findCash(authUser.getUserId());

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.toResponseDto(point)), HttpStatus.OK);
    }




}
