package com.danimo.restaurant.dish.application.inputports;


import com.danimo.restaurant.dish.application.usecases.createdish.CreateDishDto;
import com.danimo.restaurant.dish.domain.Dish;

public interface CreatingDishInputPort {
    Dish createDish(CreateDishDto dto);
}
