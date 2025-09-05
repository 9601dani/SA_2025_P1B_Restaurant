package com.danimo.restaurant.order.application.usecases.createorder;

import com.danimo.restaurant.dish.domain.DishId;
import com.danimo.restaurant.order.domain.entity.Item;
import com.danimo.restaurant.order.domain.vo.ItemId;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreationOrderItemDto {
    private final UUID dishId;
    private final String dishName;
    private final int quantity;
    private final BigDecimal unitPrice;

    public Item toDomain(){
        return new Item(
                ItemId.generate().getItemId(),
                DishId.fromUuid(dishId),
                dishName,
                quantity,
                unitPrice,
                null
        );
    }
}
