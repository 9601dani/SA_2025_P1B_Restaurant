package com.danimo.restaurant.dish.application.outputports.persistence;

import com.danimo.restaurant.dish.domain.Dish;

public interface StoringDishOutputPort {
    Dish save(Dish dish);
}
