package com.backend.domain.point.application;


import com.backend.domain.order.domain.Order;
import com.backend.domain.point.dao.PointHistoryRepository;
import com.backend.domain.point.domain.PointHistory;
import com.backend.domain.point.domain.PointType;
import com.backend.domain.point.dto.PointResponseDto;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {
    private final PointHistoryRepository pointHistoryRepository;

    private final UserRepository userRepository;

    @Transactional
    public Long addCash(User user, int price, PointType pointType) {
        addPointHistory(user, price, pointType);
        long userRestCash = getUserRestCash(user);
        log.info("service / 포인트 사용 및 충전 시작");
        Long newRestCash = userRestCash + price;
        user.setRestCash(newRestCash);
        userRepository.save(user);

        return newRestCash;
    }

    public void addPointHistory(User user, int price, PointType pointType) {
        long userRestCash = getUserRestCash(user);
        PointHistory pointHistory = PointHistory.builder()
                .user(user)
                .cash(price)
                .pointType(pointType)
                .createdAt(LocalDateTime.now())
                .restCash(userRestCash + price)
                .build();
        log.info("service / 포인트 사용,충전 내역 저장");
        pointHistoryRepository.save(pointHistory);
    }

    private long getUserRestCash(User user) {
        long userRestCash = 0L;
        if (user.getRestCash() != null) {
            userRestCash = user.getRestCash();
        }
        return userRestCash;
    }

    public Long getRestCash(User user) {
        return user.getRestCash();
    }

    @Transactional
    public void pay(Order order, Long userId) {
        log.info("service / 포인트로 상품결제 시작");
        User user = userRepository.findById(userId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
        Long restCash = user.getRestCash();
        int payPrice = order.getOrderTotalPrice();
        if (payPrice > restCash) {
            throw new BusinessLogicException(ExceptionCode.NOT_ENOUGH_POINT);

        }
        addCash(user, payPrice * -1, PointType.PayPoint);
    }

    public Page<PointResponseDto> getPointList(Long userId, Pageable pageable) {
        log.info("Service/ userId : " + userId + "포인트내역 조회 시작");
        List<PointHistory> pointHistoryList = pointHistoryRepository.findPointHistoryList(userId, pageable);

        List<PointResponseDto> pointResponseDtoList = new ArrayList<>();
        for (PointHistory pointHistory : pointHistoryList) {
            PointResponseDto pointResponseDto = new PointResponseDto(pointHistory);
            pointResponseDtoList.add(pointResponseDto);
        }

        Long totalQuantity = pointHistoryRepository.countPoint(userId);

        return new PageImpl<PointResponseDto>(pointResponseDtoList, pageable, totalQuantity);
    }
}
