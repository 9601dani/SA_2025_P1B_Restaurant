package com.danimo.restaurant.dish.application.usecases.createdish;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.DishAlreadyExistException;
import com.danimo.restaurant.dish.application.inputports.CreatingDishInputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.FindingDishByNameOutputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.StoringDishOutputPort;
import com.danimo.restaurant.dish.domain.Dish;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class CreateDishUseCase implements CreatingDishInputPort {
    private final FindingDishByNameOutputPort findingDishByNameOutputPort;
    private final StoringDishOutputPort storingDishOutputPort;

    @Autowired
    public CreateDishUseCase(FindingDishByNameOutputPort findingDishByNameOutputPort, StoringDishOutputPort storingDishOutputPort) {
        this.findingDishByNameOutputPort = findingDishByNameOutputPort;
        this.storingDishOutputPort = storingDishOutputPort;
    }


    @Override
    public Dish createDish(CreateDishDto dto) {
        if(findingDishByNameOutputPort.findByName(dto.getName()).isPresent()) {
            throw new DishAlreadyExistException("Ya existe el platillo "+ dto.getName());
        }

        Dish newDish = dto.toDomain();

        return storingDishOutputPort.save(newDish);
    }
}
