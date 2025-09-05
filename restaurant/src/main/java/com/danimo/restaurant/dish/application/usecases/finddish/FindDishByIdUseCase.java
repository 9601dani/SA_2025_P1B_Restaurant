package com.danimo.restaurant.dish.application.usecases.finddish;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.DishNotFoundException;
import com.danimo.restaurant.dish.application.inputports.FindingDishByIdInputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.FindingDishByIdOutputPort;
import com.danimo.restaurant.dish.domain.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

@UseCase
public class FindDishByIdUseCase implements FindingDishByIdInputPort {
    private final FindingDishByIdOutputPort findingDishByIdOutputPort;

    @Autowired
    public FindDishByIdUseCase(FindingDishByIdOutputPort findingDishByIdOutputPort) {
        this.findingDishByIdOutputPort = findingDishByIdOutputPort;
    }


    @Override
    public Dish findById(String id) {
        return findingDishByIdOutputPort.findById(UUID.fromString(id))
                .orElseThrow(() -> new DishNotFoundException("No existe el platillo"));
    }
}
