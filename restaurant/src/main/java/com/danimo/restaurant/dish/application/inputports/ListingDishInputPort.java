package com.danimo.restaurant.dish.application.inputports;

import com.danimo.restaurant.dish.domain.Dish;

import java.util.List;

public interface ListingDishInputPort {
    List<Dish> getAllDishes();
}
