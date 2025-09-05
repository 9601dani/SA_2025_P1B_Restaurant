package com.danimo.restaurant.order.application.inputports;

import com.danimo.restaurant.common.application.exceptions.OrderNotFoundException;
import com.danimo.restaurant.order.application.usecases.updateorder.UpdateOrderDto;
import com.danimo.restaurant.order.domain.aggregate.Order;

public interface UpdatingOrderInputPort {
    Order updateOrder(UpdateOrderDto dto) throws OrderNotFoundException;
}
