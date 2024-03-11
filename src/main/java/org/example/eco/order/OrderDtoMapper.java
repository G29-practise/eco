package org.example.eco.order;

import lombok.RequiredArgsConstructor;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.order.dto.OrderCreateDto;
import org.example.eco.order.dto.OrderResponseDto;
import org.example.eco.order.dto.OrderUpdateDto;
import org.example.eco.order.entity.Order;
import org.example.eco.productSet.ProductSetDtoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class OrderDtoMapper extends GenericMapper<Order, OrderCreateDto, OrderResponseDto, OrderUpdateDto> {
    private final ModelMapper mapper;
    private final ProductSetDtoMapper productSetDtoMapper;

    @Override
    public Order toEntity(OrderCreateDto orderCreateDto) {
        return mapper.map(orderCreateDto, Order.class);
    }

    @Override
    public OrderResponseDto toResponseDto(Order order) {
        return mapper.map(order, OrderResponseDto.class);

    }

    @Override
    public void toEntity(OrderUpdateDto orderUpdateDto, Order order) {
        mapper.map(orderUpdateDto, order);
    }
}
