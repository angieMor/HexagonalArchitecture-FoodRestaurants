package com.powerup.square.domain.api;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);
    List<Order> getAllOrder();

}
