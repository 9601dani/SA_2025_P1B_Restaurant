package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class OrderItemResponseDto {
    UUID dishId;
    String dishName;
    int quantity;
    BigDecimal unitPrice;
    BigDecimal lineTotal;

    public static OrderItemResponseDto fromDomain(com.danimo.restaurant.order.domain.entity.Item item) {
        return new OrderItemResponseDto(
                item.getDishId().getId(),
                item.getDishNameSnapshot(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.calculateLineTotal().toBigDecimal()
        );
    }
}
