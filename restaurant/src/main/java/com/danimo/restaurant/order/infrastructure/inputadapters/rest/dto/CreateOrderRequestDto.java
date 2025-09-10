package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.createorder.CreationOrderDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class CreateOrderRequestDto {
    String description;
    @NotBlank
    UUID locationId;
    @NotBlank
    String nit;
    @NotBlank
    UUID userEmployeeId;
    String discountCode;
    BigDecimal discountAmount;
    List<CreateOrderItemRequestDto> items;

    public CreationOrderDto toApplicationDto() {
        return new CreationOrderDto(
                description,
                locationId,
                nit,
                userEmployeeId,
                discountCode,
                discountAmount,
                items.stream().map(CreateOrderItemRequestDto::toApplicationDto).toList()
        );
    }
}
