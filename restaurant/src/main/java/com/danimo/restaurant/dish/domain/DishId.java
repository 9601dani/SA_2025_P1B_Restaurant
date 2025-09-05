package com.danimo.restaurant.dish.domain;

import lombok.Value;

import java.util.UUID;

@Value
public class DishId {
    private final UUID id;

    private DishId(UUID id) {
        this.id = id;
    }

    public static DishId generate() {
        return new DishId(UUID.randomUUID());
    }

    public static DishId fromUuid(UUID uuid) {
        return new DishId(uuid);
    }
}
