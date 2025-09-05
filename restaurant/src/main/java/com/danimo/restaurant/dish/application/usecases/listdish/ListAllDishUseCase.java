package com.danimo.restaurant.dish.application.usecases.listdish;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.dish.application.inputports.ListingDishInputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.FindingAllDishesOutputPort;
import com.danimo.restaurant.dish.domain.Dish;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UseCase
public class ListAllDishUseCase implements ListingDishInputPort {
    private final FindingAllDishesOutputPort findingAllDishesOutputPort;

    @Autowired
    public ListAllDishUseCase(FindingAllDishesOutputPort findingAllDishesOutputPort) {
        this.findingAllDishesOutputPort = findingAllDishesOutputPort;
    }

    @Override
    public List<Dish> getAllDishes() {
        return this.findingAllDishesOutputPort.findAllDishes();
    }
}
