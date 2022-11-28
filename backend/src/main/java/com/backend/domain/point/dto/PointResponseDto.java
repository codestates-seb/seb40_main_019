package com.backend.domain.point.dto;

import com.backend.domain.point.domain.PointType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PointResponseDto {
    int pointId;
    int cash;
    private PointType pointType;
}
