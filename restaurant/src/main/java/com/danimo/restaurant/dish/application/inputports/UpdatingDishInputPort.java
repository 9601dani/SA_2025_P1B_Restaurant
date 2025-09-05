package com.danimo.restaurant.dish.application.inputports;

import com.danimo.restaurant.dish.application.usecases.updatedish.UpdateDishDto;
import com.danimo.restaurant.dish.domain.Dish;

public interface UpdatingDishInputPort {
    Dish updateDish(UpdateDishDto dto);
}
