package com.danimo.restaurant.order.application.usecases.updatestate;

import com.danimo.restaurant.common.application.annotations.UseCase;
import com.danimo.restaurant.common.application.exceptions.EntityNotFoundException;
import com.danimo.restaurant.order.application.inputports.UpdatingStateOrderInputPort;
import com.danimo.restaurant.order.application.outputports.persistence.FindingOrderByIdOutputPort;
import com.danimo.restaurant.order.application.outputports.persistence.StoringOrderOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.CreateMovementOutputPort;
import com.danimo.restaurant.order.application.outputports.rest.CreatingBillOutputPort;
import com.danimo.restaurant.order.domain.aggregate.Order;
import com.danimo.restaurant.order.domain.vo.OrderId;
import com.danimo.restaurant.order.domain.vo.OrderStatus;
import com.danimo.restaurant.order.infrastructure.outputadapters.rest.dto.CreateMovementRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
@UseCase
public class UpdateStateUseCase implements UpdatingStateOrderInputPort {
    private final FindingOrderByIdOutputPort findingOrderByIdOutputPort;
    private final StoringOrderOutputPort storingOrderOutputPort;
    private final CreatingBillOutputPort creatingBillOutputPort;
    private final CreateMovementOutputPort createMovementOutputPort;

    @Autowired
    public UpdateStateUseCase(FindingOrderByIdOutputPort findingOrderByIdOutputPort, StoringOrderOutputPort storingOrderOutputPort,
                              CreatingBillOutputPort creatingBillOutputPort, CreateMovementOutputPort createMovementOutputPort) {
        this.findingOrderByIdOutputPort = findingOrderByIdOutputPort;
        this.storingOrderOutputPort = storingOrderOutputPort;
        this.creatingBillOutputPort = creatingBillOutputPort;
        this.createMovementOutputPort = createMovementOutputPort;
    }


    @Override
    public Order update(UpdateStateDto dto) {
        Order order = findingOrderByIdOutputPort.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("La orden no existe"));

        order.changeStatus(dto.getStatus());
        Order savedOrder = storingOrderOutputPort.save(order);
        if (order.getStatus() == OrderStatus.COMPLETED) {
          if(!creatingBillOutputPort.createBill(savedOrder)){
              throw new EntityNotFoundException("La factura no pudo generarse para la orden");
          }

            var movementDto = CreateMovementRequestDto.generateDto(
                    "RESTAURANT",
                    "CREDIT",
                    "Pago por venta en restaurante",
                    savedOrder.getTotal(),
                    savedOrder.getLocationId(),
                    ""
            );

            if (!createMovementOutputPort.isSuccess(movementDto)) {
                throw new RuntimeException("No se pudo registrar el movimiento de venta en reportes");
            }

            var taxMovement = CreateMovementRequestDto.generateDto(
                    "RESTAURANT",
                    "DEBIT",
                    "Pago por impuesto sobre el pedido",
                    savedOrder.getTax().getTax(),
                    savedOrder.getLocationId(),
                    ""
            );

            if (!createMovementOutputPort.isSuccess(taxMovement)) {
                throw new RuntimeException("No se pudo registrar el movimiento de impuesto en reportes");
            }
        }

        return savedOrder;
    }
}
