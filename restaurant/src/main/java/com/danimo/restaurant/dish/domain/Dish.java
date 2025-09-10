package com.danimo.restaurant.dish.domain;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@DomainEntity
@AllArgsConstructor
public class Dish {
    private DishId id;
    private String name;
    private String description;
    private DishPrice price;
    private Category category;
    private DishCreatedAt createdAt;
    private DishUpdatedAt updatedAt;
    private String imageUrl;
    private DishCost dishCost;

    public Dish update(String name, String description, DishPrice price, String category,
                       String imageUrl, DishCost dishCost) {
        return new Dish(
                this.id,
                name,
                description,
                price,
                new Category(category),
                this.createdAt,
                DishUpdatedAt.generate(),
                imageUrl,
                dishCost
        );
    }
}
