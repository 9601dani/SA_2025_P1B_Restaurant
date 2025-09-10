package com.danimo.restaurant.dish.domain;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class DishCost {
    private BigDecimal cost;

    public DishCost(BigDecimal cost) {
        if(cost == null){
            throw new IllegalArgumentException("El costo no puede ser nulo");
        }
        if(cost.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El costo no puede ser negativo");
        }

        this.cost = cost;
    }

    public static DishCost fromBigDecimal(BigDecimal price) {
        return new DishCost(price);
    }
}
