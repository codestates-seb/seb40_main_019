package com.backend.domain.order.dao;

import com.backend.domain.order.domain.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> searchOrder(Long userId) ;
}
