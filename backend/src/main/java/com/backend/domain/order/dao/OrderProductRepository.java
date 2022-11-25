package com.backend.domain.order.dao;

import com.backend.domain.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>  {
    List<OrderProduct> findByProductProductId(long productId);

}
