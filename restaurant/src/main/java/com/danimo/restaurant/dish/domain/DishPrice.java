package com.danimo.restaurant.dish.domain;

import lombok.Value;

import java.math.BigDecimal;
@Value
public class DishPrice {
    private BigDecimal price;

    public DishPrice(BigDecimal price) {
        if(price == null){
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }
        if(price.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        this.price = price;
    }

    public static DishPrice fromBigDecimal(BigDecimal price) {
        return new DishPrice(price);
    }

}
