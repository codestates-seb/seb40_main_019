package com.backend.domain.point.dao;

import com.backend.domain.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
}
