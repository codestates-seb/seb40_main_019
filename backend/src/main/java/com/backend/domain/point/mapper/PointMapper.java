package com.backend.domain.point.mapper;

import com.backend.domain.point.domain.Point;
import com.backend.domain.point.dto.PointResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointMapper {

    PointResponseDto toResponseDto(Point point);
    List<PointResponseDto> toResponseDtos(List<Point> pointList);
}
