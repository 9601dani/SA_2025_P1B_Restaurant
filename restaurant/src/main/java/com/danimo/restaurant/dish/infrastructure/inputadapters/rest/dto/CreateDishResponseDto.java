package com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.dish.domain.Dish;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateDishResponseDto {
    private String id;
    private String name;

    public static CreateDishResponseDto fromDomain(Dish dish) {
        return new CreateDishResponseDto(dish.getId().getId().toString(), dish.getName());
    }
}
