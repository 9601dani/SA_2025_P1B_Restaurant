package com.danimo.restaurant.order.application.outputports.rest;

import java.util.UUID;

public interface ExistLocationOutputPort {
    boolean existLocation(UUID locationId);
}
