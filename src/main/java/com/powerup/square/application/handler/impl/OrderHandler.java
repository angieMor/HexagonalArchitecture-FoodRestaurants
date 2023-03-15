package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.application.dto.OrderGeneralResponse;
import com.powerup.square.application.dto.OrdersStateRequest;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.application.mapper.IOrderResponseMapper;
import com.powerup.square.domain.api.IOrderPlatesServicePort;
import com.powerup.square.domain.api.IOrderServicePort;
import com.powerup.square.domain.api.IPlateServicePort;
import com.powerup.square.domain.api.IRestaurantServicePort;
import com.powerup.square.domain.exception.PendingOrderAlreadyExistsException;
import com.powerup.square.domain.exception.PlateIsNotFromThisRestaurantException;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IPlatePersistencePort;
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
    private final IOrderResponseMapper iOrderResponseMapper;

    private final IOrderPlatesServicePort iOrderPlatesServicePort;

    private final IRestaurantServicePort iRestaurantServicePort;

    private final IPlatePersistencePort iPlatePersistencePort;
    private final IPlateServicePort iPlateServicePort;


    public OrderHandler(IOrderServicePort iOrderServicePort, IOrderRequestMapper iOrderRequestMapper, IOrderResponseMapper iOrderResponseMapper, IOrderPlatesServicePort iOrderPlatesServicePort, IRestaurantServicePort iRestaurantServicePort, IPlatePersistencePort iPlatePersistencePort, IPlateServicePort iPlateServicePort) {
        this.iOrderServicePort = iOrderServicePort;
        this.iOrderRequestMapper = iOrderRequestMapper;
        this.iOrderResponseMapper = iOrderResponseMapper;
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.iPlatePersistencePort = iPlatePersistencePort;
        this.iPlateServicePort = iPlateServicePort;
    }

    @Override
    public void saveOrder(OrderGeneralRequest orderGeneralRequest) {
        Order order = iOrderRequestMapper.toOrder(orderGeneralRequest);

        // Validates if client did an order before
        if(iOrderServicePort.existsByIdClient(order.getIdClient())) {

            // If state is different from done, it won't let do the order
            if(iOrderServicePort.getOrderByIdClient(order.getIdClient()).getState() != "Done") {
                throw new PendingOrderAlreadyExistsException();
            }
        }

        // Validates if plateId requested is from the restaurant given
        for(int x = 0; x<=orderGeneralRequest.getIdPlates().size()-1;x++) {
            if(iPlateServicePort.getPlate(orderGeneralRequest.getIdPlates().get(x)).getRestaurant().getId() !=
                    orderGeneralRequest.getIdRestaurant()) {
                throw new PlateIsNotFromThisRestaurantException();
            }
        }


        Date date = new java.util.Date();

        order.setId(-1L);
        order.setDate(date);
        order.setState("Pending");
        order.setRestaurant(iRestaurantServicePort.getRestaurant(orderGeneralRequest.getIdRestaurant()));

        // Saving data in orders table
        iOrderServicePort.saveOrder(order);
        Order newOrder = iOrderServicePort.getOrderByIdClient(order.getIdClient());


        List<OrderPlates> listOrderPlates = new ArrayList<>();

        for(int x = 0; x<=orderGeneralRequest.getIdPlates().size()-1; x++){
            OrderPlates orderPlates = new OrderPlates(
                    newOrder,
                    iPlatePersistencePort.getPlate(orderGeneralRequest.getIdPlates().get(x)),
                    orderGeneralRequest.getAmountPlates().get(x)
            );
            listOrderPlates.add(orderPlates);
        }

        //Saving data in order_plates table
        iOrderPlatesServicePort.saveOrderPlates(listOrderPlates);
    }

    @Override
    public List<OrderGeneralResponse> getAllOrdersByState(OrdersStateRequest ordersStateRequest) {
        return iOrderResponseMapper.toOrderResponseList(iOrderServicePort.getAllOrdersByState(ordersStateRequest));
    }

}
