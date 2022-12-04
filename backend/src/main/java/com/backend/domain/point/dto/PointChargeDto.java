package com.backend.domain.point.dto;

import com.backend.domain.point.domain.PointType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PointChargeDto {


    private int price;//충전할금액

    private PointType pointType;

}
