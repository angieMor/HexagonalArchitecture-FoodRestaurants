package com.powerup.square.application.handler.impl;

import com.google.gson.Gson;
import com.powerup.square.application.dto.order.*;
import com.powerup.square.application.dto.user.UsersPin;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.application.mapper.IOrderResponseMapper;
import com.powerup.square.domain.api.*;
import com.powerup.square.domain.exception.PlateIsNotFromThisRestaurantException;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.infraestructure.configuration.TwilioConfiguration;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderHandlerTest {

    @InjectMocks
    OrderHandler orderHandler;

    @Mock
    IOrderServicePort iOrderServicePort;
    @Mock
    IOrderRequestMapper iOrderRequestMapper;
    @Mock
    IOrderResponseMapper iOrderResponseMapper;
    @Mock
    IOrderPlatesServicePort iOrderPlatesServicePort;
    @Mock
    IRestaurantServicePort iRestaurantServicePort;
    @Mock
    IPlatePersistencePort iPlatePersistencePort;
    @Mock
    IPlateServicePort iPlateServicePort;
    @Mock
    IEmployeeServicePort iEmployeeServicePort;
    @Mock
    TwilioConfiguration twilioConfiguration;

    @Test
    void saveOrder(){
        //Given
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderGeneralRequest orderGeneralRequest = SaveOrderHandlerDataTest.ObtainOrderGeneralRequest();

        when(iOrderRequestMapper.toOrder(orderGeneralRequest)).thenReturn(order);
        when(iOrderServicePort.getOrderByIdClient(anyLong())).thenReturn(order);
        when(iPlatePersistencePort.getPlate(anyLong())).thenReturn(SavePlateHandlerDataTest.obtainPlate());
        System.out.println(orderGeneralRequest);

        orderHandler.saveOrder(orderGeneralRequest);

        verify(iOrderServicePort).saveOrder(order);
    }

    @Test
    void GetAllOrdersByState(){
        OrdersStateRequest ordersStateRequest = SaveOrderHandlerDataTest.obtainOrdersStaterequest();
        int page = 0;
        int size = 2;

        when(iEmployeeServicePort.getEmployee(anyLong())).thenReturn(SaveEmployeeHandlerDataTest.obtainEmployee());

        orderHandler.getAllOrdersByState(page, size, ordersStateRequest);
    }

    @Test
    void updateOrderToAsignIt(){
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        order.setState("Preparing");

        OrderUpdateRequest orderUpdateRequest = SaveOrderHandlerDataTest.obtainOrderUpdateRequest();

        when(iOrderServicePort.getOrderById(anyLong())).thenReturn(order);

        orderHandler.updateOrderToAsignIt(orderUpdateRequest);
    }

    @Test
    void notifyOrderIsReady(){
        OrderIsReadyRequest orderIsReadyRequest = SaveOrderHandlerDataTest.obtainOrderIsReady();
        Order order= SaveOrderHandlerDataTest.obtainOrder();
        order.setState("Preparing");

        when(iOrderServicePort.existsByIdClient(anyLong())).thenReturn(true);
        when(iOrderServicePort.getOrderByIdClient(anyLong())).thenReturn(order,SaveOrderHandlerDataTest.obtainOrder());

        orderHandler.notifyOrderIsReady(orderIsReadyRequest);
    }

    @Test
    void setOrderToDelivered(){
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderDeveliveredRequest orderDeveliveredRequest = SaveOrderHandlerDataTest.obtainOrderDeliveredRequest();
        UsersPin usersPin = SaveOrderHandlerDataTest.obtainUsersPin();

        UsersPin userPinList = new UsersPin();
        userPinList.setIdClient(usersPin.getIdClient());
        userPinList.setPin(usersPin.getPin());

        JSONObject json = new JSONObject();
        json.put("idClient", userPinList.getIdClient());
        json.put("pin", userPinList.getPin());

        if(UsersPin.clientList == null) {
            UsersPin.clientList = new HashMap<>();
        }

        UsersPin.clientList.put(userPinList.getIdClient(), new Gson().fromJson(json.toString(), HashMap.class));

        order.setState("Ready");

        when(iOrderServicePort.existsByIdClient(anyLong())).thenReturn(true);
        when(iOrderServicePort.getOrderByIdClient(anyLong())).thenReturn(order);

        orderHandler.setOrderToDelivered(orderDeveliveredRequest);
    }

    @Test
    void setOrderToCanceled(){
        Order order = SaveOrderHandlerDataTest.obtainOrder();
        OrderToBeCanceledRequest orderToBeCanceledRequest = SaveOrderHandlerDataTest.obtainOrderToBeCanceledRequest();

        when(iOrderServicePort.existsByIdClient(anyLong())).thenReturn(true);
        when(iOrderServicePort.getOrderByIdClient(anyLong())).thenReturn(order);

        orderHandler.setOrderToCanceled(orderToBeCanceledRequest);

    }
}
