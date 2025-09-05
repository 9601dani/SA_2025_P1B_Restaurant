package com.danimo.restaurant.order.application.inputports;

import com.danimo.restaurant.order.application.usecases.createorder.CreationOrderDto;
import com.danimo.restaurant.order.domain.aggregate.Order;

public interface CreatingOrderInputPort {
    Order createOrder(CreationOrderDto creationOrderDto);
}
