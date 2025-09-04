package com.danimo.restaurant.category.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class CategoryResponseDto {
    private String name;

    public static CategoryResponseDto fromDomain(Category category) {
        return new CategoryResponseDto(category.getName());
    }
}
