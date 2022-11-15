package com.backend.domain.order.dao;

import com.backend.domain.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
