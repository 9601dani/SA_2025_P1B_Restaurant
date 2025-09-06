package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.application.usecases.updatestate.UpdateStateDto;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateStatusRequestDto {
    @NotBlank
    UUID id;
    @NotBlank
    String status;

    public UpdateStateDto toApplicationDto() {
        return new UpdateStateDto(
                id,
                status != null ? OrderStatus.valueOf(status) : null
        );
    }
}
