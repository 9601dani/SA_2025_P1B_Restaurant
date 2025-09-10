package com.danimo.restaurant.dish.application.usecases.updatedish;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.DishNotFoundException;
import com.danimo.restaurant.dish.application.inputports.UpdatingDishInputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.FindingDishByIdOutputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.StoringDishOutputPort;
import com.danimo.restaurant.dish.domain.Dish;
import com.danimo.restaurant.dish.domain.DishCost;
import com.danimo.restaurant.dish.domain.DishUpdatedAt;
import org.springframework.beans.factory.annotation.Autowired;
@UseCase
public class UpdateDishUseCase implements UpdatingDishInputPort {
    private final FindingDishByIdOutputPort findingDishByIdOutputPort;
    private final StoringDishOutputPort storingDishOutputPort;

    @Autowired
    public UpdateDishUseCase(FindingDishByIdOutputPort findingDishByIdOutputPort, StoringDishOutputPort storingDishOutputPort) {
        this.findingDishByIdOutputPort = findingDishByIdOutputPort;
        this.storingDishOutputPort = storingDishOutputPort;
    }

    @Override
    public Dish updateDish(UpdateDishDto dto) {
        Dish dish = findingDishByIdOutputPort.findById(dto.getDishId().getId())
                .orElseThrow(() -> new DishNotFoundException("No se encontro el platillo"));

        Dish updated = dish.update(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getCategory().getName(),
                dto.getImageUrl(),
                dto.getDishCost()
        );

        return storingDishOutputPort.save(updated);
    }
}
