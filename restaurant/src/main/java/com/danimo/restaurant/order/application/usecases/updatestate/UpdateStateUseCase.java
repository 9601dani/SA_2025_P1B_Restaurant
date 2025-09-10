package com.danimo.restaurant.order.application.usecases.updatestate;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.EntityNotFoundException;
import com.danimo.restaurant.order.application.inputports.UpdatingStateOrderInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.StoringOrderOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.CreatingBillOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.vo.OrderId;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
@UseCase
public class UpdateStateUseCase implements UpdatingStateOrderInputPort {
    private final FindingOrderByIdOutputPort findingOrderByIdOutputPort;
    private final StoringOrderOutputPort storingOrderOutputPort;
    private final CreatingBillOutputPort creatingBillOutputPort;

    @Autowired
    public UpdateStateUseCase(FindingOrderByIdOutputPort findingOrderByIdOutputPort, StoringOrderOutputPort storingOrderOutputPort,
                              CreatingBillOutputPort creatingBillOutputPort) {
        this.findingOrderByIdOutputPort = findingOrderByIdOutputPort;
        this.storingOrderOutputPort = storingOrderOutputPort;
        this.creatingBillOutputPort = creatingBillOutputPort;
    }


    @Override
    public Order update(UpdateStateDto dto) {
        Order order = findingOrderByIdOutputPort.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("La orden no existe"));

        order.changeStatus(dto.getStatus());
        Order savedOrder = storingOrderOutputPort.save(order);
        if (order.getStatus() == OrderStatus.COMPLETED) {
            System.out.println("GENERARE FACTURA");
          if(!creatingBillOutputPort.createBill(savedOrder)){
              throw new EntityNotFoundException("La factura no pudo generarse para la orden");
          }
        }

        return savedOrder;
    }
}
