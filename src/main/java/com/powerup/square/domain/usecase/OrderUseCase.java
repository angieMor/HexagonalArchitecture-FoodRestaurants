package com.powerup.square.domain.usecase;

import com.powerup.square.application.dto.order.OrdersStateRequest;
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
    public List<Order> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest, Long restaurantOfEmployee) {
        return orderPersistencePort.getAllOrdersByState(page, size, ordersStateRequest, restaurantOfEmployee);
    }

    @Override
    public Order getOrderByIdClient(Long idClient) {
        return orderPersistencePort.getOrderByIdClient(idClient);
    }

    @Override
    public boolean existsByIdClient(Long idClient) {
        return orderPersistencePort.existsByIdClient(idClient);
    }


}

