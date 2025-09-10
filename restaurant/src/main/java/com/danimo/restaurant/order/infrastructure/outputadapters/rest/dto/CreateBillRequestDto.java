package com.danimo.restaurant.order.infrastructure.outputadapters.rest.dto;

import com.danimo.restaurant.order.domain.aggregate.Order;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class CreateBillRequestDto {
    private UUID locationId;
    private String clientId;
    private String clientName;
    private String billTypeService;
    private String moneda;
    private BigDecimal discount;
    private List<CreateBillItemRequestDto> items;

    public static CreateBillRequestDto fromOrder(Order order) {
        return new CreateBillRequestDto(
                order.getLocationId(),
                order.getIdClient(),
                order.getDescription(),
                "RESTAURANT",
                "GTQ",
                order.getDiscount().getDiscount(),
                order.getItems().stream()
                        .map(i -> new CreateBillItemRequestDto(
                                i.getDishNameSnapshot(),
                                i.getQuantity(),
                                i.getUnitPrice()
                        )).toList()
        );
    }
}
