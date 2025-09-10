package com.danimo.restaurant.order.infrastructure.outputadapters.rest.dto;

import com.danimo.restaurant.order.domain.aggregate.Order;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateMovementRequestDto {
    private String serviceType;
    private String paymentType;
    private String description;
    private BigDecimal amount;
    private UUID locationId;
    private String locationName;

    public static CreateMovementRequestDto generateDto(String serviceType, String paymentType, String description, BigDecimal amount, UUID locationId, String locationName) {
        return new CreateMovementRequestDto(
                serviceType,
                paymentType,
                description,
                amount,
                locationId,
                locationName

        );
    }
}
