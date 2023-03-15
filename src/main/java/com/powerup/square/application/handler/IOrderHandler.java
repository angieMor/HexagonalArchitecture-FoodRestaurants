package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.application.dto.OrderGeneralResponse;
import com.powerup.square.application.dto.OrdersStateRequest;

import java.util.List;

public interface IOrderHandler {

    void saveOrder(OrderGeneralRequest orderGeneralRequest);

    List<OrderGeneralResponse> getAllOrdersByState(OrdersStateRequest ordersStateRequest);
}
