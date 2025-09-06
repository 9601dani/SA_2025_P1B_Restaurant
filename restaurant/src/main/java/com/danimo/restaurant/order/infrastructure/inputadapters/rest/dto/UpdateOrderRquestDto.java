package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.updateorder.UpdateOrderDto;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class UpdateOrderRquestDto {
    @NotBlank
    UUID orderId;
    String description;
    @NotBlank
    UUID locationId;
    @NotBlank
    String nit;
    @NotBlank
    UUID userEmployeeId;
    String status;
    List<UpdateOrderItemRequestDto> items;

    public UpdateOrderDto toApplicationDto() {
        return new UpdateOrderDto(
                orderId,
                description,
                locationId,
                nit,
                userEmployeeId,
                status != null ? OrderStatus.valueOf(status) : null,
                items != null ? items.stream().map(UpdateOrderItemRequestDto::toApplicationDto).toList() : List.of()
        );
    }
}
