package com.danimo.restaurant.category.application.usecases.createcategory;

import com.danimo.restaurant.category.application.inputports.CreatingCategoryInputPort;
import com.danimo.restaurant.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.danimo.restaurant.category.application.outputports.persistence.StoringCategoryOutputPort;
import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.CategoryAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class CreateCategoryUseCase implements CreatingCategoryInputPort {
    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;
    private final StoringCategoryOutputPort storingCategoryOutputPort;
    @Autowired
    public CreateCategoryUseCase(FindingCategoryByNameOutputPort findingCategoryByNameOutputPort,
                                 StoringCategoryOutputPort storingCategoryOutputPort) {
        this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
        this.storingCategoryOutputPort = storingCategoryOutputPort;
    }

    @Override
    public Category create(CreateCategoryDto dto) throws CategoryAlreadyExistException {
        if(findingCategoryByNameOutputPort.findByName(dto.getName().toUpperCase()).isPresent()) {
            throw new CategoryAlreadyExistException(dto.getName());
        }

        Category newCategory = dto.toDomain();

        return storingCategoryOutputPort.save(newCategory);
    }
}
