package com.danimo.restaurant.dish.domain;

import lombok.Value;

import java.time.LocalDateTime;
@Value
public class DishCreatedAt {
    private final LocalDateTime createdAt;

    public DishCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static DishCreatedAt generate(){
        return new DishCreatedAt(LocalDateTime.now());
    }

    public static DishCreatedAt fromDomain(LocalDateTime createdAt) {
        return new DishCreatedAt(createdAt);
    }
}
