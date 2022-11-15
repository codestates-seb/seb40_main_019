package com.backend.domain.order.dao;

import com.backend.domain.order.domain.Order;
import com.backend.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> , OrderRepositoryCustom{
   // @Query("select p from Order p where p.user.userId = :userId")
    //Page<Order> findByUser(@Param("userId") long userId, Pageable pageable);


}
