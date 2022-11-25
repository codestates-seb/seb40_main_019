package com.backend.domain.order.dto;

import com.backend.domain.order.domain.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartOrderProductDto {

    private Long productId;
    private int quantity;


}
