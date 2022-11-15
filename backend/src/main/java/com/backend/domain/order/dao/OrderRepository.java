package com.backend.domain.order.dao;

import com.backend.domain.order.domain.Order;
import com.backend.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o " +
            "where o.user.email = :email " +
            "order by o.orderDate desc"
    )
    List<Order> findOrders(@Param("email") String email, Pageable pageable);

    @Query("select count(o) from Order o " +
            "where o.user.email = :email"
    )
    Long countOrder(@Param("email") String email);

}
