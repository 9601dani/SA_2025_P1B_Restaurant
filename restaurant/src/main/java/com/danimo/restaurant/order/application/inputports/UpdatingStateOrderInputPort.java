package com.danimo.restaurant.order.application.inputports;

import com.danimo.restaurant.order.application.usecases.updatestate.UpdateStateDto;

public interface UpdatingStateOrderInputPort {
    boolean update(UpdateStateDto dto);
}
