package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IRestaurantPersistencePort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort iOrderServicePort;

    private final IOrderRequestMapper iOrderRequestMapper;

    private final IOrderPlatesServicePort iOrderPlatesServicePort;

    private final IRestaurantPersistencePort iRestaurantPersistencePort;


    public OrderHandler(IOrderServicePort iOrderServicePort, IOrderRequestMapper iOrderRequestMapper, IOrderPlatesServicePort iOrderPlatesServicePort, IRestaurantPersistencePort iRestaurantPersistentPort) {
        this.iOrderServicePort = iOrderServicePort;
        this.iOrderRequestMapper = iOrderRequestMapper;
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iRestaurantPersistencePort = iRestaurantPersistentPort;
    }

    @Override
    public void saveOrder(OrderGeneralRequest orderGeneralRequest) {
        Order order = iOrderRequestMapper.toOrder(orderGeneralRequest);

        Date date = new java.util.Date();

        order.setId(1L);
        order.setDate(date);
        order.setState("Pending");
        order.setRestaurant(iRestaurantPersistencePort.getRestaurant(orderGeneralRequest.getIdRestaurant()).getId());

        iOrderServicePort.saveOrder(order);

        List<OrderPlates> listOrderPlates = new ArrayList<>();

//        for(Long OrderId : orderGeneralRequest.getIdPlates()){
//
//            OrderPlates orderPlates = new OrderPlates(order.getId(),
//            orderGeneralRequest.getIdPlates().get(OrderId.intValue()),
//            orderGeneralRequest.getAmountPlates().get(OrderId.intValue()));
//
//            listOrderPlates.add(orderPlates);
//        }
        for(int x = 0; x<=orderGeneralRequest.getIdPlates().size()-1; x++){
            OrderPlates orderPlates = new OrderPlates(
                    order.getId(),
                    orderGeneralRequest.getIdPlates().get(x),
                    orderGeneralRequest.getAmountPlates().get(x)
            );

            listOrderPlates.add(orderPlates);
        }

        iOrderPlatesServicePort.saveOrderPlates(listOrderPlates);

    }
}
