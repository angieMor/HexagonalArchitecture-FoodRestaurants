package com.powerup.square.domain.api;

import com.powerup.square.application.dto.order.OrderIsReadyRequest;
import com.powerup.square.application.dto.order.OrdersStateRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);
    List<Order> getAllOrdersByState(int page, int size, OrdersStateRequest state, Long restaurantOfTheEmployee);

    Order getOrderByIdClient(Long idClient);
    boolean existsByIdClient(Long idClient);

    Order getOrderById(Long idOrder);

    void updateOrderToAsignIt(List<Order> order);

}
