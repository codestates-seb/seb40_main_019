package com.backend.domain.order.mapper;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.dto.OrderDto;
import com.backend.domain.order.dto.OrderPatchDto;
import com.backend.domain.order.dto.OrderResponseDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);

    List<OrderDto> toDtos(List<Order> orders);

    OrderResponseDto orderToResponseDto(Order order);

    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);


}
