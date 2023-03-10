package com.powerup.square.domain.spi;

import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {

    void saveOrder(Order order);
    List<Order> getAllOrder();

}
