package com.danimo.restaurant.dish.domain;

import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@DomainEntity
@AllArgsConstructor
public class Dish {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private LocalDateTime createdAt;
}
