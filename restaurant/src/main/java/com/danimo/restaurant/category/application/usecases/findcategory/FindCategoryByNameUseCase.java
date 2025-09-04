package com.danimo.restaurant.category.application.usecases.findcategory;

import com.danimo.restaurant.category.application.inputports.FindingCategoryByNameInputPort;
import com.danimo.restaurant.category.application.outputports.persistence.FindingCategoryByNameOutputPort;
import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class FindCategoryByNameUseCase implements FindingCategoryByNameInputPort {
    private final FindingCategoryByNameOutputPort findingCategoryByNameOutputPort;

    @Autowired
    FindCategoryByNameUseCase(FindingCategoryByNameOutputPort findingCategoryByNameOutputPort) {
        this.findingCategoryByNameOutputPort = findingCategoryByNameOutputPort;
    }


    @Override
    public Category findByName(String name) throws CategoryNotFoundException {
        return findingCategoryByNameOutputPort.findByName(name).orElseThrow(() -> new CategoryNotFoundException("La categoria no existe"));
    }
}
