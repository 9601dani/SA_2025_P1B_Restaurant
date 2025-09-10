package com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity.mapper;

import com.danimo.restaurant.dish.domain.DishId;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.entity.Item;
import com.danimo.restaurant.order.domain.vo.*;
import com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity.ItemDbEntity;
import com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity.OrderDbEntity;
import com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity.OrderDiscountEmbeddable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderPersistenceMapper {
    public OrderDbEntity toDbEntity(Order order) {
        OrderDbEntity entity = new OrderDbEntity();
        entity.setId(order.getId().getOrderId());
        entity.setDescription(order.getDescription());
        entity.setLocationId(order.getLocationId());
        entity.setIdClient(order.getIdClient());
        entity.setStatus(order.getStatus());
        entity.setSubTotal(order.getSubTotal().getSubtotal());
        entity.setDiscount(toEmbeddable(order.getDiscount()));
        entity.setTax(order.getTax().getTax());
        entity.setTotal(order.getTotal());
        entity.setCreatedAt(order.getCreatedAt().getCreatedAt());
        entity.setUpdatedAt(order.getUpdatedAt().getUpdatedAt());
        entity.setUserEmployeeId(order.getUserEmployeeId());

        List<ItemDbEntity> items = order.getItems().stream()
                .map(i -> toDbEntity(i, entity))
                .toList();
        entity.setItems(items);

        return entity;
    }

    public Order toDomain(OrderDbEntity entity) {
        List<Item> items = entity.getItems().stream()
                .map(this::toDomainItem)
                .toList();

        OrderDiscount discount = toDomainDiscount(entity.getDiscount());

        return new Order(
                OrderId.fromUUID(entity.getId()),
                entity.getDescription(),
                entity.getLocationId(),
                entity.getIdClient(),
                entity.getStatus(),
                OrderSubtotal.fromBigDecimal(entity.getSubTotal()),
                discount,
                OrderTax.fromBigDecimal(entity.getTax()),
                entity.getTotal(),
                OrderCreatedAt.fromLocalDateTime(entity.getCreatedAt()),
                OrderUpdatedAt.fromLocalDateTime(entity.getUpdatedAt()),
                entity.getUserEmployeeId(),
                items
        );
    }

    private ItemDbEntity toDbEntity(Item item, OrderDbEntity order) {
        ItemDbEntity entity = new ItemDbEntity();
        entity.setId(item.getId());
        entity.setDishId(item.getDishId().getId());
        entity.setDishName(item.getDishNameSnapshot());
        entity.setQuantity(item.getQuantity());
        entity.setUnitPrice(item.getUnitPrice());
        entity.setLineTotal(item.calculateLineTotal().toBigDecimal());
        entity.setOrder(order);
        return entity;
    }

    private Item toDomainItem(ItemDbEntity entity) {
        return new Item(
                entity.getId(),
                DishId.fromUuid(entity.getDishId()),
                entity.getDishName(),
                entity.getQuantity(),
                entity.getUnitPrice(),
                ItemLineTotal.fromBigDecimal(entity.getLineTotal())
        );
    }

    private OrderDiscountEmbeddable toEmbeddable(OrderDiscount discount) {
        if (discount == null) {
            return new OrderDiscountEmbeddable(BigDecimal.ZERO, "");
        }
        BigDecimal amount = discount.getDiscount() != null ? discount.getDiscount() : BigDecimal.ZERO;
        String code = discount.getCode() != null ? discount.getCode() : "";
        return new OrderDiscountEmbeddable(amount, code);
    }

    private OrderDiscount toDomainDiscount(OrderDiscountEmbeddable emb) {
        BigDecimal amount = (emb != null && emb.getAmount() != null) ? emb.getAmount() : BigDecimal.ZERO;
        String code = (emb != null && emb.getCode() != null) ? emb.getCode() : "";
        return new OrderDiscount(amount, code);
    }
}
