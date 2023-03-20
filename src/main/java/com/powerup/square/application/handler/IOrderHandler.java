package com.powerup.square.application.handler;

import com.powerup.square.application.dto.order.*;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderHandler {

    void saveOrder(OrderGeneralRequest orderGeneralRequest);

    List<OrderGeneralResponse> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest);


    List<OrderPlates> getOrderPlatesById(Long id);

    void updateOrderToAsignIt(OrderUpdateRequest orderUpdateRequest);

    void notifyOrderIsReady(OrderIsReadyRequest orderIsReadyRequest);

    void setOrderToDelivered(OrderDeveliveredRequest orderDeveliveredRequest);

    void setOrderToCanceled(OrderToBeCanceledRequest orderToBeCanceledRequest);
}
