package com.backend.domain.point.application;

import com.backend.domain.point.dao.PointRepository;
import com.backend.domain.point.domain.Point;
import com.backend.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRepository pointRepository;

    public Point addPoint(User user, long cash) {
        Point point = Point.builder()
                .user(user)
                .cash(cash)
                .build();
        pointRepository.save(point);
        return point  ;
    }
}
