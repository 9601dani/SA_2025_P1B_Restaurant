package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.util.UUID;

@Value
public class OrderId {
    private final UUID orderId;

    public OrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public static OrderId generate() {
        return new OrderId(UUID.randomUUID());
    }

    public static OrderId fromUUID(UUID uuid) {
        return new OrderId(uuid);
    }
}
