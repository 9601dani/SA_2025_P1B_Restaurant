package com.danimo.restaurant.order.application.usecases.updateorder;

import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.entity.Item;
import com.danimo.restaurant.order.domain.vo.OrderDiscount;
import com.danimo.restaurant.order.domain.vo.OrderId;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import com.danimo.restaurant.order.domain.vo.OrderUpdatedAt;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class UpdateOrderDto {
    private UUID orderId;
    private String description;
    private UUID locationId;
    private String nit;
    private UUID userEmployeeId;
    private OrderStatus status;
    private String code;
    private BigDecimal discount;
    private List<UpdateOrderItemDto> items;

    public Order applyChanges(Order current){
        List<Item> updatedItems = (items != null && !items.isEmpty())
                ? items.stream().map(UpdateOrderItemDto::toDomain).toList()
                : current.getItems();

        return new Order(
                OrderId.fromUUID(orderId),
                description != null ? description : current.getDescription(),
                locationId != null ? locationId : current.getLocationId(),
                nit != null ? nit : current.getIdClient(),
                status != null ? status : current.getStatus(),
                null,
                current.getDiscount() != null ? current.getDiscount() : OrderDiscount.fromBigdecimalAndCode(discount,code),
                null,
                BigDecimal.ZERO,
                current.getCreatedAt(),
                OrderUpdatedAt.generate(),
                userEmployeeId != null ? userEmployeeId : current.getUserEmployeeId(),
                updatedItems
        );
    }
}
