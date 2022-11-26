package com.backend.global.utils;

import com.backend.domain.order.application.OrderService;
import com.backend.domain.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class ScheduleTasks {
    private final OrderService orderService;
    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(ScheduleTasks.class);

    //@Scheduled(fixedDelay = 5000)//테스트용 5초마다 실행
    @Transactional
    @Scheduled(cron = "0 0 7 * * *") //매일아침 7시마다 그 전에 배송중으로 바꾼 주문들을 전부 배송완료로 변경
    public void AutoOrderTask() {
        orderService.autoUpdate();
        log.info("배송된 주문들이 배송완료처리 되었습니다");
    }

    // Scheduled 매일 06시에 실행
    @Transactional
    @Scheduled(cron = "0 0 6 * * *")
    public void deleteGustAccount() {
        userService.deleteGustAccount();
    }
}
