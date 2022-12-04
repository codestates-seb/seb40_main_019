package com.backend.domain.point.mapper;

import com.backend.domain.point.domain.PointHistory;
import com.backend.domain.point.dto.PointResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointMapper {

    PointResponseDto toResponseDto(PointHistory pointHistory);
    List<PointResponseDto> toResponseDtos(List<PointHistory> pointHistoryList);
}
