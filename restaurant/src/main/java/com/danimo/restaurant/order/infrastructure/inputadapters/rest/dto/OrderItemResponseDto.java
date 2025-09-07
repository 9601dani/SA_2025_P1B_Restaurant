package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.domain.entity.Item;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class OrderItemResponseDto {
    @NotBlank
    UUID dishId;
    @NotBlank
    String dishName;
    @NotBlank
    int quantity;
    @NotBlank
    BigDecimal unitPrice;
    BigDecimal lineTotal;

    public static OrderItemResponseDto fromDomain(Item item) {
        return new OrderItemResponseDto(
                item.getDishId().getId(),
                item.getDishNameSnapshot(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.calculateLineTotal().toBigDecimal()
        );
    }
}
