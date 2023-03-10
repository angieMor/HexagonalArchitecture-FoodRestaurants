package com.powerup.square.domain.api;

import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderPlatesServicePort {

    void saveOrderPlates(List<OrderPlates> orderPlates);
    List<OrderPlates> getAllOrderPlatesByOrderId(Long id);
}
