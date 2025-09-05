package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.createorder.CreationOrderItemDto;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateOrderItemRequestDto {
    UUID dishId;
    String dishName;
    int quantity;
    BigDecimal unitPrice;

    public CreationOrderItemDto toApplicationDto() {
        return new com.danimo.restaurant.order.application.usecases.createorder.CreationOrderItemDto(
                dishId,
                dishName,
                quantity,
                unitPrice
        );
    }
}
