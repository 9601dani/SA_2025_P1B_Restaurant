package com.danimo.restaurant.category.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.category.application.usecases.createcategory.CreateCategoryDto;
import com.danimo.restaurant.category.domain.CategoryCreatedAt;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CategoryRequestDto {
    @NotBlank
    private String name;

    public CreateCategoryDto toDomain(){
        return new CreateCategoryDto(
                name,
                CategoryCreatedAt.generate()
        );
    }
}
