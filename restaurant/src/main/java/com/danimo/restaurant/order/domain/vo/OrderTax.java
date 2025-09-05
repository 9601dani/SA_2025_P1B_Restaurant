package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderTax {
    private BigDecimal tax;

    public OrderTax(BigDecimal tax) {
        if(tax == null){
            throw new IllegalArgumentException("El impuesto debe ser mayor que 0");
        }
        if(tax.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El impuesto debe ser mayor que 0");
        }
        this.tax = tax;
    }

    public static OrderTax fromBigDecimal(BigDecimal tax) {
        return new OrderTax(tax);
    }
}
