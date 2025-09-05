package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderDiscount {

    private BigDecimal discount;

    public OrderDiscount(BigDecimal discount) {
        if(discount == null) {
            throw new NullPointerException("El descuento no puede ser nulo");
        }

        if(discount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }

        this.discount = discount;
    }
    public static OrderDiscount fromBigDecimal(BigDecimal discount) {
        return new OrderDiscount(discount);
    }
    public static OrderDiscount zero() {
        return new OrderDiscount(BigDecimal.ZERO);
    }

}
