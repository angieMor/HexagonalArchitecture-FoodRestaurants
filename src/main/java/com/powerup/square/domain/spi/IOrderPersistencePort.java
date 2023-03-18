package com.powerup.square.domain.spi;

import com.powerup.square.application.dto.order.OrderIsReadyRequest;
import com.powerup.square.application.dto.order.OrdersStateRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {

    void saveOrder(Order order);
    List<Order> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest, Long restaurantOfEmployee);

    Order getOrderByIdClient(Long idClient);

    boolean existsByIdClient(Long idClient);

    void updateOrderToAsignIt(List<Order> order);
    Order getOrderById(Long idOrder);

}
