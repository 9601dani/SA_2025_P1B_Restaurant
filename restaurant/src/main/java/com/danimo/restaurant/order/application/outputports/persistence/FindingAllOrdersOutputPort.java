package com.danimo.restaurant.order.application.outputports.persistence;

import com.danimo.restaurant.order.domain.aggregate.Order;

import java.util.List;

public interface FindingAllOrdersOutputPort {
    List<Order> findingAllOrders();
}
