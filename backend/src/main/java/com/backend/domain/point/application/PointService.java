package com.backend.domain.point.application;


import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.point.dao.PointRepository;
import com.backend.domain.point.domain.Point;
import com.backend.domain.point.domain.PointType;
import com.backend.domain.point.dto.PointResponseDto;
import com.backend.domain.point.mapper.PointMapper;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final PointMapper mapper;

    @Transactional
    public Integer addCash(User user, int price, PointType pointType) {
        Point point = addPoint(user, price, pointType);
        log.info("service / 포인트 사용 및 충전 시작");
        int newRestCash = user.getRestCash() + price;
        user.setRestCash(newRestCash);
        userRepository.save(user);

        return newRestCash;
    }

    public Point addPoint(User user, int price, PointType pointType) {

        Point point = Point.builder()
                .user(user)
                .cash(price)
                .pointType(pointType)
                .createdAt(LocalDateTime.now())
                .build();

        log.info("service / 포인트 사용,충전 내역 저장");
        pointRepository.save(point);

        return point;
    }

    public int getRestCash(User user) {
        return user.getRestCash();
    }

    @Transactional
    public void pay(Order order) {
        log.info("service / 포인트로 상품결제 시작");
        User user = order.getUser();
        int restCash = user.getRestCash();
        int payPrice = order.getOrderTotalPrice();
        if (payPrice > restCash) {
            throw new BusinessLogicException(ExceptionCode.NOT_ENOUGH_POINT);

        }
        addCash(user, payPrice * -1, PointType.PayPoint);
        orderRepository.save(order);

    }

    public Page<PointResponseDto> getPointList(Long userId, Pageable pageable) {
        log.info("Service/ userId : " + userId + "포인트내역 조회 시작");
        List<Point> pointList = pointRepository.findPointList(userId, pageable);
        List<PointResponseDto> pointResponseDtoList = mapper.toResponseDtos(pointList);
        Long totalQuantity = pointRepository.countPoint(userId);

        return new PageImpl<PointResponseDto>(pointResponseDtoList, pageable, totalQuantity);
    }
}
