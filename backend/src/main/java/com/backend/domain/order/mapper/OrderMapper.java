package com.backend.domain.order.mapper;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.dto.OrderPatchDto;
import com.backend.domain.order.dto.OrderPostDto;
import com.backend.domain.order.dto.OrderResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderPostDto orderPostDto);
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);
    OrderResponseDto orderToOrderResponseDto(Order order);

    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);

}
