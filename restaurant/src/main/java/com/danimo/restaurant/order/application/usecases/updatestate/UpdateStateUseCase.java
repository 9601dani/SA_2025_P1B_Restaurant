package com.danimo.restaurant.order.application.usecases.updatestate;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.EntityNotFoundException;
import com.danimo.restaurant.order.application.inputports.UpdatingStateOrderInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.StoringOrderOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.vo.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
@UseCase
public class UpdateStateUseCase implements UpdatingStateOrderInputPort {
    private final FindingOrderByIdOutputPort findingOrderByIdOutputPort;
    private final StoringOrderOutputPort storingOrderOutputPort;

    @Autowired
    public UpdateStateUseCase(FindingOrderByIdOutputPort findingOrderByIdOutputPort, StoringOrderOutputPort storingOrderOutputPort) {
        this.findingOrderByIdOutputPort = findingOrderByIdOutputPort;
        this.storingOrderOutputPort = storingOrderOutputPort;
    }


    @Override
    public boolean update(UpdateStateDto dto) {
        Order order = findingOrderByIdOutputPort.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("La orden no existe"));

        order.changeStatus(dto.getStatus());

        storingOrderOutputPort.save(order);

        return true;
    }
}
