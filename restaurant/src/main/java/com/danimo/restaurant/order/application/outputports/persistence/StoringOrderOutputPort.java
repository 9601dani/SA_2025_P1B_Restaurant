package com.danimo.restaurant.order.application.outputports.persistence;

import com.danimo.restaurant.order.domain.aggregate.Order;

public interface StoringOrderOutputPort {
    Order save(Order order);
}
