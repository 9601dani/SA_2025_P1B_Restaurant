package com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.dish.domain.Dish;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class DishResponse {
    private final String id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String imageUrl;
    private final BigDecimal dishCost;

    public static DishResponse fromDomain(Dish dish) {
        return new DishResponse(
                dish.getId().getId().toString(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice().getPrice(),
                dish.getCategory().getName(),
                dish.getCreatedAt().getCreatedAt(),
                dish.getUpdatedAt().getUpdatedAt(),
                dish.getImageUrl(),
                dish.getDishCost().getCost()
        );
    }
}
