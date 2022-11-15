package com.backend.domain.order.mapper;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.dto.OrderPatchDto;

import com.backend.domain.order.dto.OrderRequestDto;
import com.backend.domain.order.dto.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class})
public interface OrderMapper {
    @Mapping(target = "orderProducts" , source = "products")
    Order toOrderEntity(OrderRequestDto dto);

    OrderResponseDto toOrderResponseDto(Order order);

    Order toOrderEntity(OrderPatchDto orderPatchDto);


    List<OrderResponseDto> toOrderResponseDtoList(List<Order> order);

    List<Order> toEntityList(List<OrderResponseDto> dtos);

    default OrderProduct productInfoToOrderProduct(OrderRequestDto.ProductInfo productInfo) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.getProduct().setProductId(productInfo.getProductId());
        orderProduct.getProduct().setProductName(productInfo.getProductName());
        orderProduct.setProductQuantity(productInfo.getQuantity());
        orderProduct.getProduct().setPrice(productInfo.getPrice());
        return orderProduct;
    }

}
