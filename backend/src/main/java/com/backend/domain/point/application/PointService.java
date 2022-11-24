package com.backend.domain.point.application;

import com.backend.domain.order.application.OrderService;
import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.point.dao.PointRepository;
import com.backend.domain.point.domain.Point;
import com.backend.domain.point.dto.PointChargeDto;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

  /*  public Point addCash(User user, PointChargeDto pointChargeDto) {
    int chargePrice = pointChargeDto.getPrice();
    Point point = pointRepository.findByUser(user.getUserId());
    int restCash = point.getCash();
    int cash = restCash + chargePrice;
    point.setCash(cash);
    pointRepository.save(point);
    return point;
    }

    public Point findCash(Long userId) {
       Point point =  pointRepository.findByUser(userId);
       return point;
    }*/

    public Integer addCash(User user, int price) {
        Point point = addCash2(user, price);
        int newRestCash = user.getRestCash() + price;
        user.setRestCash(newRestCash);
        userRepository.save(user);
//컨트롤러에서 price로 변환
        return newRestCash;
    }

    public Point addCash2(User user, int price) {
        Point point = Point.builder()
                .user(user)
                .cash(price)
                .build();

        pointRepository.save(point);

        return point;
    }

    public int getRestCash(User user) {
        return user.getRestCash();
    }

    @Transactional
    public void pay(Order order) {
        User user = order.getUser();
        int restCash = user.getRestCash();
        int payPrice = order.getOrderTotalPrice();
        if (payPrice > restCash) {
            throw new RuntimeException("포인트가 부족합니다.");
        }
        addCash(user, payPrice * -1);
        orderRepository.save(order);

    }
}





