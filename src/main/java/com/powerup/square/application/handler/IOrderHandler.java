package com.powerup.square.application.handler;

import com.powerup.square.application.dto.order.OrderGeneralRequest;
import com.powerup.square.application.dto.order.OrderGeneralResponse;
import com.powerup.square.application.dto.order.OrderUpdateRequest;
import com.powerup.square.application.dto.order.OrdersStateRequest;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderHandler {

    void saveOrder(OrderGeneralRequest orderGeneralRequest);

    List<OrderGeneralResponse> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest);


    List<OrderPlates> getOrderPlatesById(Long id);

    void updateOrderToAsignIt(OrderUpdateRequest orderUpdateRequest);
}
