package com.danimo.restaurant.order.application.inputports;

import com.danimo.restaurant.order.application.usecases.updatestate.UpdateStateDto;
import com.danimo.restaurant.order.domain.aggregate.Order;

public interface UpdatingStateOrderInputPort {
    Order update(UpdateStateDto dto);
}
