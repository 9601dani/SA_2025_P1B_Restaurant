package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class OrderUpdatedAt {
    private final LocalDateTime updatedAt;

    public OrderUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static OrderUpdatedAt generate(){
        return new OrderUpdatedAt(LocalDateTime.now());
    }

    public static OrderUpdatedAt fromLocalDateTime(LocalDateTime updatedAt) {
        return new OrderUpdatedAt(updatedAt);
    }
}
