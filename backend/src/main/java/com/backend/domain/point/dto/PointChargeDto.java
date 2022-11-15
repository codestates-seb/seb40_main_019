package com.backend.domain.point.dto;

import com.backend.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PointChargeDto {

    private long userId;

    private int Price;//충전할금액

}
