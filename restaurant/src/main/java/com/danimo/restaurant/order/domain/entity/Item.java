package com.danimo.restaurant.order.domain.entity;

import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import com.danimo.restaurant.dish.domain.DishId;
import com.danimo.restaurant.order.domain.vo.ItemLineTotal;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@DomainEntity
@AllArgsConstructor
public class Item {
    private UUID id;
    private DishId dishId;
    private String dishNameSnapshot;
    private int quantity;
    private BigDecimal unitPrice;
    private ItemLineTotal lineTotal;

    public ItemLineTotal calculateLineTotal() {
        BigDecimal total = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return ItemLineTotal.fromBigDecimal(total);
    }

}
