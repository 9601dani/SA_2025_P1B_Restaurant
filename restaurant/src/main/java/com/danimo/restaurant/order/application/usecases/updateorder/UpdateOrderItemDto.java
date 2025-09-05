package com.danimo.restaurant.order.application.usecases.updateorder;

import com.danimo.restaurant.dish.domain.DishId;
import com.danimo.restaurant.order.domain.entity.Item;
import com.danimo.restaurant.order.domain.vo.ItemId;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class UpdateOrderItemDto {
    private UUID itemId;
    private UUID dishId;
    private String dishName;
    private int quantity;
    private BigDecimal unitPrice;

    public Item toDomain(){
        return new Item(
                itemId != null ? itemId : ItemId.generate().getItemId(),
                DishId.fromUuid(dishId),
                dishName,
                quantity,
                unitPrice,
                null
        );
    }
}
