package com.danimo.restaurant.order.application.usecases.updatestate;

import com.danimo.restaurant.order.domain.vo.OrderStatus;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateStateDto {
    private final UUID id;
    private final OrderStatus status;

}
