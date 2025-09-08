package com.danimo.restaurant.dish.infrastructure.inputadapters.rest.dto;

import com.danimo.restaurant.dish.application.usecases.createdish.CreateDishDto;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class CreateDishRequestDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;

    public CreateDishDto toDomain() {
        return new CreateDishDto(
                name,
                description,
                price,
                category,
                createdAt,
                updatedAt,
                imageUrl
        );
    }
}
