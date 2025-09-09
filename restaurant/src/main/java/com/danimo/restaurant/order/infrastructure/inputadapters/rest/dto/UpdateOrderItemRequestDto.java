package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.updateorder.UpdateOrderItemDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateOrderItemRequestDto {
    @NotBlank
    UUID itemId;
    @NotBlank
    UUID dishId;
    @NotBlank
    String dishName;
    @NotBlank
    int quantity;
    @NotBlank
    BigDecimal unitPrice;

    public UpdateOrderItemDto toApplicationDto() {
        return new UpdateOrderItemDto(
                itemId,
                dishId,
                dishName,
                quantity,
                unitPrice
        );
    }
}
