package com.danimo.restaurant.dish.domain;

import lombok.Value;

import java.time.LocalDateTime;
@Value
public class DishUpdatedAt {
    private final LocalDateTime updatedAt;

    public DishUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static DishUpdatedAt generate(){
        return new DishUpdatedAt(LocalDateTime.now());
    }

    public static DishUpdatedAt fromDomain(LocalDateTime updatedAt) {
        return new DishUpdatedAt(updatedAt);
    }
}
