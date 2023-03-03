package com.powerup.square.domain.usecase;

import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void saveOrder(Order order) {
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderPersistencePort.getAllOrder();
    }

    @Override
    public Order getOrder(Long id) {
        return orderPersistencePort.getOrder(id);
    }

    @Override
    public void cancelOrder(Long id) {
        orderPersistencePort.cancelOrder(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderPersistencePort.updateOrder(order);
    }
}

