package com.danimo.restaurant.order.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.order.domain.aggregate.Order;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
public class OrderResponse {
    UUID orderId;
    String description;
    String nit;
    UUID locationId;
    UUID userEmployeeId;
    String status;
    BigDecimal subtotal;
    BigDecimal tax;
    String code;
    BigDecimal discount;
    BigDecimal total;
    List<OrderItemResponseDto> items;
    LocalDateTime createdAt;

    public static OrderResponse fromDomain(Order order) {
        return new OrderResponse(
                order.getId().getOrderId(),
                order.getDescription(),
                order.getIdClient(),
                order.getLocationId(),
                order.getUserEmployeeId(),
                order.getStatus().name(),
                order.getSubTotal().getSubtotal(),
                order.getTax().getTax(),
                order.getDiscount().getCode(),
                order.getDiscount().getDiscount(),
                order.getTotal(),
                order.getItems().stream().map(OrderItemResponseDto::fromDomain).toList(),
                order.getCreatedAt().getCreatedAt()
        );
    }
}

