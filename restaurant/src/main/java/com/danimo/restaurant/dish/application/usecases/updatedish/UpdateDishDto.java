package com.danimo.restaurant.dish.application.usecases.updatedish;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.dish.domain.*;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor
public class UpdateDishDto {
    private DishId dishId;
    private String name;
    private String description;
    private DishPrice price;
    private Category category;
    private DishCreatedAt createdAt;
    private DishUpdatedAt updatedAt;
    private String portion;
    private String imageUrl;
    private DishCost dishCost;

}
