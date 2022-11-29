package com.backend.domain.point.dto;

import com.backend.domain.point.domain.PointHistory;
import com.backend.domain.point.domain.PointType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class PointResponseDto {
    long pointHistoryId;
    int cash;
    private PointType pointType;
    int restCash;
    private LocalDateTime createdAt;

    public PointResponseDto(PointHistory pointHistory) {
        this.pointHistoryId = pointHistory.getPointHistoryId();
        this.cash = pointHistory.getCash();
        this.pointType = pointHistory.getPointType();
        this.restCash = pointHistory.getRestCash();
        this.createdAt = pointHistory.getCreatedAt();

    }

}
