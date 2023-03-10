package com.powerup.square.domain.spi;

import com.powerup.square.domain.model.OrderPlates;

import java.util.List;

public interface IOrderPlatesPersistencePort {

    void saveOrderPlates(List<OrderPlates> orderPlates);

    List<OrderPlates> getAllOrderPlatesByOrderId(Long id);

}
