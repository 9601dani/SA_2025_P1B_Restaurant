package com.danimo.restaurant.dish.infrastructure.outputadapters.persistence;

import com.danimo.restaurant.category.application.inputports.ListingAllCategoriesInputPort;
import com.danimo.restaurant.category.domain.Category;
import com.danimo.restaurant.common.infrastructure.annotations.PersistenceAdapter;
import com.danimo.restaurant.dish.application.outputports.persistence.FindingAllDishesOutputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.FindingDishByIdOutputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.FindingDishByNameOutputPort;
import com.danimo.restaurant.dish.application.outputports.persistence.StoringDishOutputPort;
import com.danimo.restaurant.dish.domain.Dish;
import com.danimo.restaurant.dish.infrastructure.outputadapters.persistence.entity.DishDbEntity;
import com.danimo.restaurant.dish.infrastructure.outputadapters.persistence.entity.mapper.DishPersistenceMapper;
import com.danimo.restaurant.dish.infrastructure.outputadapters.persistence.repository.DishDbEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.config.DefaultsBindHandlerAdvisor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
public class DishRepositoryOutputAdapter implements FindingDishByIdOutputPort,
        FindingDishByNameOutputPort, FindingAllDishesOutputPort, StoringDishOutputPort {

    private final DishDbEntityJpaRepository dishDbEntityJpaRepository;
    private final DishPersistenceMapper dishPersistenceMapper;

    @Autowired
    public DishRepositoryOutputAdapter(DishDbEntityJpaRepository dishDbEntityJpaRepository, DishPersistenceMapper persistenceMapper, DefaultsBindHandlerAdvisor.MappingsProvider mappingsProvider) {
        this.dishDbEntityJpaRepository = dishDbEntityJpaRepository;
        this.dishPersistenceMapper = persistenceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Dish> findById(UUID id) {
        return dishDbEntityJpaRepository.findById(id)
                .map(dishPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Dish> findByName(String name) {
        return dishDbEntityJpaRepository.findByName(name)
                .map(dishPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Dish save(Dish dish) {
        DishDbEntity dishSaved = dishDbEntityJpaRepository.save(dishPersistenceMapper.toDbEntity(dish));

        return dishPersistenceMapper.toDomain(dishSaved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Dish> findAllDishes() {
        return dishDbEntityJpaRepository.findAll()
                .stream()
                .map(dishPersistenceMapper::toDomain)
                .toList();
    }
}
