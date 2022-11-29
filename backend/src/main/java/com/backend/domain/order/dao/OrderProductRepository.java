package com.backend.domain.order.dao;

import com.backend.domain.order.domain.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>  {
    List<OrderProduct> findByProductProductId(long productId);


    @Query("select op from OrderProduct op " +
            "where op.order.user.userId = :userId AND op.product.productId= :productId"
    )
    OrderProduct  findOrderProduct(@Param("userId") Long userId, @Param("productId") Long productId);

    @Query("select op from OrderProduct op " +
            "where op.order.user.userId = :userId AND op.product.productId= :productId"
    )
    Optional<OrderProduct>  findByOrderProduct(@Param("userId") Long userId, @Param("productId") Long productId);
}
