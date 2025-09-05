package com.danimo.restaurant.dish.application.inputports;

import com.danimo.restaurant.dish.domain.Dish;

public interface FindingDishByIdInputPort {
    Dish findById(String id);
}
