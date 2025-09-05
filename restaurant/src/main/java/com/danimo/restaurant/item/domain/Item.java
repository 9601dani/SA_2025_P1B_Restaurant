package com.danimo.restaurant.item.domain;

import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import com.danimo.restaurant.dish.domain.DishId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@DomainEntity
@AllArgsConstructor
public class Item {
    private UUID id;
    private DishId dishId;
    private String dishName;
    private int quiantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;
}
