package com.powerup.square.domain.api;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.application.dto.OrdersStateRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);
    List<Order> getAllOrdersByState(OrdersStateRequest state);

    Order getOrderByIdClient(Long idClient);
    boolean existsByIdClient(Long idClient);

}
