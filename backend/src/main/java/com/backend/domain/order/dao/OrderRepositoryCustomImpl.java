package com.backend.domain.order.dao;

import com.backend.domain.order.domain.Order;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;

import static com.backend.domain.order.domain.QOrder.order;
import static com.backend.domain.order.domain.QOrderProduct.orderProduct;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> searchOrder(Long userId) {
        return queryFactory.select(order)
                .from(order)
                .leftJoin(order.orderProducts, orderProduct)
                .fetchJoin()
                .where(order.user.userId.eq(userId))
                .distinct().fetch();
    }
}