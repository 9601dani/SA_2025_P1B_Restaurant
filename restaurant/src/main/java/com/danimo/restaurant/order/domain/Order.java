package com.danimo.restaurant.order.domain;

import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import com.danimo.restaurant.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@DomainEntity
@AllArgsConstructor
public class Order {
    private UUID id;
    private String description;
    private UUID locationId;
    private String usernameClient;
    private String status;
    private BigDecimal subTotal;
    private BigDecimal discount;
    private BigDecimal tax;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String usernameEmployee;
    private List<Item> items;

}
