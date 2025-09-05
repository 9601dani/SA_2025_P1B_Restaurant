package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderSubtotal {
    private BigDecimal subtotal;

    public OrderSubtotal(BigDecimal subtotal) {
        if(subtotal == null){
            throw new NullPointerException("Subtotal no puede ser null");
        }

        if(subtotal.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Subtotal no puede ser negativo");
        }

        this.subtotal = subtotal;
    }

    public static OrderSubtotal fromBigDecimal(BigDecimal subtotal) {
        return new OrderSubtotal(subtotal);
    }
}
