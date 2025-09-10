package com.danimo.restaurant.dish.application.usecases.createdish;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.dish.domain.*;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class CreateDishDto {

    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;
    private BigDecimal dishCost;

    public Dish toDomain() {
        return new Dish(
                DishId.generate(),
                name,
                description,
                DishPrice.fromBigDecimal(price),
                new Category(category),
                DishCreatedAt.generate(),
                DishUpdatedAt.generate(),
                imageUrl,
                DishCost.fromBigDecimal(dishCost)
        );

    }
}
