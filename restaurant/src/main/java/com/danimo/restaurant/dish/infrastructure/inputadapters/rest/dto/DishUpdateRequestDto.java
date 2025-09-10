package com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.dish.application.usecases.updatedish.UpdateDishDto;
import com.danimo.restaurant.dish.domain.*;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Value
public class DishUpdateRequestDto {
    private final String id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final String category;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String portion;
    private final String imageUrl;
    private final BigDecimal dishCost;

    public UpdateDishDto toDomain(){
        return new UpdateDishDto(
                DishId.fromUuid(UUID.fromString(id)),
                name,
                description,
                DishPrice.fromBigDecimal(price),
                new Category(category),
                DishCreatedAt.fromDomain(createdAt),
                DishUpdatedAt.fromDomain(updatedAt),
                portion,
                imageUrl,
                DishCost.fromBigDecimal(dishCost)
        );
    }

}
