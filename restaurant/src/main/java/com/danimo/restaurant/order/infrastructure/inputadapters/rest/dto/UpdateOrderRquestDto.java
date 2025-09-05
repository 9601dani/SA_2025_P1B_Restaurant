package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.updateorder.UpdateOrderDto;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
public class UpdateOrderRquestDto {
    UUID orderId;
    String description;
    UUID locationId;
    String nit;
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
