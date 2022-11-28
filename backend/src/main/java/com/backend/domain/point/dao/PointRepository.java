package com.backend.domain.point.dao;

import com.backend.domain.order.domain.Order;
import com.backend.domain.point.domain.Point;
import com.backend.domain.user.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("select p from Point p where p.user.userId = :userId")
    Point findByUser(long userId);
    void deleteByUser(User user);

    @Query("select p from Point p " +
            "where p.user.userId = :userId " +
            "order by p.createdAt desc"
    )
    List<Point> findPointList(@Param("userId") Long userId, Pageable pageable);



    @Query("select count(p) from Point p " +
            "where p.user.userId = :userId"
    )
    Long countPoint(@Param("userId") Long userId);
}
