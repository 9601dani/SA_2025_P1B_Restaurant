package com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDiscountEmbeddable {
    @Column(name = "discount_amount", precision = 19, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "discount_code")
    private String code;
}
