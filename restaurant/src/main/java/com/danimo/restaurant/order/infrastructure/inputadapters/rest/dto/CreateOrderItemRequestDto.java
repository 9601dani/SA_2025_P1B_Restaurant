package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.createorder.CreationOrderItemDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateOrderItemRequestDto {
    @NotBlank
    UUID dishId;
    @NotBlank
    String dishName;
    @NotBlank
    int quantity;
    @NotBlank
    BigDecimal unitPrice;

    public CreationOrderItemDto toApplicationDto() {
        return new CreationOrderItemDto(
                dishId,
                dishName,
                quantity,
                unitPrice
        );
    }
}
