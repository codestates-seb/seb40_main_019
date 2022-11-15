package com.backend.domain.order.mapper;

import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.dto.OrderProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    OrderProductDto toDto(OrderProduct entity);
    OrderProduct toEntity(OrderProductDto dto);
    List<OrderProductDto> toDtoList(List<OrderProduct> entities);
    List<OrderProduct> toEntityList(List<OrderProductDto> dtos);
}