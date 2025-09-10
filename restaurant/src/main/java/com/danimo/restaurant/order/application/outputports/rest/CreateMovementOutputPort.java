package com.danimo.restaurant.order.application.outputports.rest;

import com.danimo.restaurant.order.infrastructure.outputadapters.rest.dto.CreateMovementRequestDto;

public interface CreateMovementOutputPort {
    boolean isSuccess(CreateMovementRequestDto dto);
}
