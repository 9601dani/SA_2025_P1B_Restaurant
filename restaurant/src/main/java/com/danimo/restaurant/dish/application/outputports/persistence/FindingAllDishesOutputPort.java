package com.danimo.restaurant.dish.application.outputports.persistence;

import com.danimo.restaurant.dish.domain.Dish;

import java.util.List;

public interface FindingAllDishesOutputPort {
    List<Dish> findAllDishes();
}
