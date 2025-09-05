package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderTotal {
    private final BigDecimal total;

    public OrderTotal(BigDecimal total) {
        if(total == null){
            throw new NullPointerException("El total es null");
        }
        if(total.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("El total es negativo");
        }
        this.total = total;
    }

    public static OrderTotal fromBigDecimal(BigDecimal total) {
        return new OrderTotal(total);
    }
}
