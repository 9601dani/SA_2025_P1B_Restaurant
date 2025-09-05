package com.danimo.restaurant.order.application.outputports.rest;

import java.util.UUID;

public interface ExistEmployeeOutputPort {
    boolean existEmployee(UUID employeeId);
}
