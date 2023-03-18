package com.powerup.square.domain.api;

import com.powerup.square.application.dto.order.OrdersStateRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);
    List<Order> getAllOrdersByState(int page, int size, OrdersStateRequest state, Long restaurantOfTheEmployee);

    Order getOrderByIdClient(Long idClient);
//    List<Order> getOrdersByRestaurantId(Long restaurantId);
    boolean existsByIdClient(Long idClient);

}
