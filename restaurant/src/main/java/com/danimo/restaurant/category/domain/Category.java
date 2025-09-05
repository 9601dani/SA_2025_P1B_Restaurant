package com.danimo.restaurant.category.domain;

import com.danimo.restaurant.common.domain.annotations.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@DomainEntity
@AllArgsConstructor
public class Category {
    private String name;
    private CategoryCreatedAt createdAt;

    public Category(String name) {
        this.name = name;
    }
}
