package com.danimo.restaurant.order.application.outputports.persistence;

import com.danimo.restaurant.order.domain.aggregate.Order;

import java.util.Optional;
import java.util.UUID;

public interface FindingOrderByIdOutputPort {
    Optional<Order> findById(UUID id);
}
