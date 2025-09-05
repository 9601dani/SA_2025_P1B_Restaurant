package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class OrderCreatedAt {
    private final LocalDateTime createdAt;

    public OrderCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static OrderCreatedAt generate(){
        return new OrderCreatedAt(LocalDateTime.now());
    }

    public static OrderCreatedAt fromLocalDateTime(LocalDateTime createdAt) {
        return new OrderCreatedAt(createdAt);
    }

}
