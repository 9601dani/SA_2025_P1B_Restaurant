package com.danimo.restaurant.order.domain.vo;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class OrderDiscount {

    private BigDecimal discount;
    private String code;

    public OrderDiscount(BigDecimal discount, String code) {
        BigDecimal safeDiscount = (discount == null) ? BigDecimal.ZERO : discount;
        String safeCode = (code == null) ? "" : code;

        if (safeDiscount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El descuento no puede ser negativo");
        }
        this.discount = safeDiscount;
        this.code = safeCode;

    }
    public static OrderDiscount fromBigdecimalAndCode(BigDecimal discount, String code) {
        return new OrderDiscount(discount, code);
    }

}
