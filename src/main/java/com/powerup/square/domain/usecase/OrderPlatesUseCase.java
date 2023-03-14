package com.powerup.square.domain.usecase;

import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IOrderPlatesPersistencePort;

import java.util.List;

public class OrderPlatesUseCase implements IOrderPlatesServicePort {
    private final IOrderPlatesPersistencePort orderPlatesPersistencePort;

    public OrderPlatesUseCase(IOrderPlatesPersistencePort orderPlatesPersistencePort) {
        this.orderPlatesPersistencePort = orderPlatesPersistencePort;
    }

    @Override
    public void saveOrderPlates(List<OrderPlates> orderPlates) {
        orderPlatesPersistencePort.saveOrderPlates(orderPlates);
    }

    @Override
    public List<OrderPlates> getAllOrderPlatesByOrderId(Long id) {
        return orderPlatesPersistencePort.getAllOrderPlatesByOrderId(id);
    }
}

