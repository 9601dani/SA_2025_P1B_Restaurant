package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.updateorder.UpdateOrderItemDto;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateOrderItemRequestDto {
    UUID itemId;
    UUID dishId;
    String dishName;
    int quantity;
    BigDecimal unitPrice;

    public UpdateOrderItemDto toApplicationDto() {
        return new com.danimo.restaurant.order.application.usecases.updateorder.UpdateOrderItemDto(
                itemId,
                dishId,
                dishName,
                quantity,
                unitPrice
        );
    }
}
