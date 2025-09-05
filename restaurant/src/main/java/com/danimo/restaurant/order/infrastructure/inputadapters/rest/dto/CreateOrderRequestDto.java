package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.createorder.CreationOrderDto;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class CreateOrderRequestDto {
    String description;
    UUID locationId;
    String nit;
    UUID userEmployeeId;
    List<CreateOrderItemRequestDto> items;

    public CreationOrderDto toApplicationDto() {
        return new CreationOrderDto(
                description,
                locationId,
                nit,
                userEmployeeId,
                items.stream().map(CreateOrderItemRequestDto::toApplicationDto).toList()
        );
    }
}
