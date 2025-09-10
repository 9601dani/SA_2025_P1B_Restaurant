package com.danimo.restaurant.order.application.usecases.createorder;

import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.entity.Item;
import com.danimo.restaurant.order.domain.vo.*;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class CreationOrderDto {
    private final String description;
    private final UUID locationId;
    private final String nit;
    private final UUID userEmployeeId;
    private final String code;
    private final BigDecimal discount;
    private final List<CreationOrderItemDto> items;

    public Order toDomain(){
        List<Item> domainItems = items.stream()
                .map(CreationOrderItemDto::toDomain)
                .toList();

        return new Order(
                OrderId.generate(),
                description,
                locationId,
                nit,
                OrderStatus.CREATED,
                null,
                OrderDiscount.fromBigdecimalAndCode(
                        discount != null ? discount : BigDecimal.ZERO,
                        code != null ? code : ""
                ),
                null,
                BigDecimal.ZERO,
                OrderCreatedAt.generate(),
                OrderUpdatedAt.generate(),
                userEmployeeId,
                domainItems
        );

    }
}
