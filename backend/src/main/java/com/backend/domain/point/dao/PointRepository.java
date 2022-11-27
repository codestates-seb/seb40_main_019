package com.backend.domain.point.dao;

import com.backend.domain.point.domain.Point;
import com.backend.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("select p from Point p where p.user.userId = :userId")
    Point findByUser(long userId);
    void deleteByUser(User user);
}
