package com.powerup.square.application.handler;

import com.powerup.square.application.dto.OrderGeneralRequest;

public interface IOrderHandler {

    void saveOrder(OrderGeneralRequest orderGeneralRequest);
}
