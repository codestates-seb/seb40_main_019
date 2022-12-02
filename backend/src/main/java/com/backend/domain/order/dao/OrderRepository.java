package com.backend.domain.order.dao;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o " +
            "where o.user.userId = :userId " +
            "order by o.createdAt desc"
    )
    List<Order> findOrders(@Param("userId") Long userId, Pageable pageable);

    @Query("select count(o) from Order o " +
            "where o.user.userId = :userId"
    )
    Long countOrder(@Param("userId") Long userId);

    @Query("select count(o) from Order o"
    )
    Long countAllOrder();


    List<Order> findByOrderStatus(OrderStatus orderStatus);


    @Query("select o from Order o " +
            "where o.user.userId = :userId " +
            "order by o.createdAt desc"
    )
    List<Order> findByUserId(@Param("userId") Long userId);



    Page<Order> findAll(Pageable pageable);
}
