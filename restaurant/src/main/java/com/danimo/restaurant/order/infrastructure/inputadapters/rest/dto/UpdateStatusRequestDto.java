package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.updatestate.UpdateStateDto;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateStatusRequestDto {
    UUID id;
    String status;

    public UpdateStateDto toApplicationDto() {
        return new UpdateStateDto(
                id,
                status != null ? OrderStatus.valueOf(status) : null
        );
    }
}
