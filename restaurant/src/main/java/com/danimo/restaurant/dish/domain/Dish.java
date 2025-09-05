package com.danimo.restaurant.dish.domain;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


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
}
