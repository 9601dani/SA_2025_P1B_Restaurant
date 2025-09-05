package com.danimo.restaurant.order.application.inputports;

import com.danimo.restaurant.order.domain.aggregate.Order;

public interface FindingOrderByIdInputPort {
    Order findById(String id);
}
