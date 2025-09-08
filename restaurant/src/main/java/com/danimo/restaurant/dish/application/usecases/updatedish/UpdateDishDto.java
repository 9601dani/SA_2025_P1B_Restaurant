package com.danimo.restaurant.dish.application.usecases.updatedish;

import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.dish.domain.DishCreatedAt;
import com.danimo.restaurant.dish.domain.DishId;
import com.danimo.restaurant.dish.domain.DishPrice;
import com.danimo.restaurant.dish.domain.DishUpdatedAt;
import lombok.AllArgsConstructor;
import lombok.Value;

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

}
