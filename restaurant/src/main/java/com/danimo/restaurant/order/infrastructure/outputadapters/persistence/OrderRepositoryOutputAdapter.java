package com.danimo.restaurant.order.infrastructure.outputadapters.persistence;

import com.danimo.restaurant.common.infrastructure.annotations.PersistenceAdapter;
import com.danimo.restaurant.order.application.outputports.persistence.FindingAllOrdersOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.StoringOrderOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity.OrderDbEntity;
import com.danimo.restaurant.order.infrastructure.outputadapters.persistence.entity.mapper.OrderPersistenceMapper;
import com.danimo.restaurant.order.infrastructure.outputadapters.persistence.repository.OrderDbEntityJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
public class OrderRepositoryOutputAdapter implements FindingAllOrdersOutputPort, FindingOrderByIdOutputPort,
        StoringOrderOutputPort {
    private final OrderDbEntityJpaRepository orderDbEntityJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Autowired
    public OrderRepositoryOutputAdapter(OrderDbEntityJpaRepository orderDbEntityJpaRepository, OrderPersistenceMapper orderPersistenceMapper) {
        this.orderDbEntityJpaRepository = orderDbEntityJpaRepository;
        this.orderPersistenceMapper = orderPersistenceMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findingAllOrders() {
        return orderDbEntityJpaRepository.findAll()
                .stream()
                .map(orderPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> findById(UUID id) {
        return orderDbEntityJpaRepository.findById(id)
                .map(orderPersistenceMapper::toDomain);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public Order save(Order order) {
        OrderDbEntity orderDbEntity = orderPersistenceMapper.toDbEntity(order);

        return orderPersistenceMapper.toDomain(orderDbEntity);
    }
}
