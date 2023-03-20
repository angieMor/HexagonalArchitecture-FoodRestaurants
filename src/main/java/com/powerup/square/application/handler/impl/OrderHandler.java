package com.powerup.square.application.handler.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;
import com.powerup.square.application.dto.order.*;
import com.powerup.square.application.dto.user.UsersPin;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.application.mapper.IOrderResponseMapper;
import com.powerup.square.application.mapper.IPlateResponseMapper;
import com.powerup.square.domain.api.*;
import com.powerup.square.domain.exception.PendingOrderAlreadyExistsException;
import com.powerup.square.domain.exception.PlateIsNotFromThisRestaurantException;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.infraestructure.configuration.TwilioConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mapstruct.control.MappingControl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.lang.Math;

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

    private final IPlateResponseMapper iPlateResponseMapper;

    private final IEmployeeServicePort iEmployeeServicePort;

    private final TwilioConfiguration twilioConfiguration;


    public OrderHandler(IOrderServicePort iOrderServicePort, IOrderRequestMapper iOrderRequestMapper, IOrderResponseMapper iOrderResponseMapper, IOrderPlatesServicePort iOrderPlatesServicePort, IRestaurantServicePort iRestaurantServicePort, IPlatePersistencePort iPlatePersistencePort, IPlateServicePort iPlateServicePort, IPlateResponseMapper iPlateResponseMapper, IEmployeeServicePort iEmployeeServicePort, TwilioConfiguration twilioConfiguration) {
        this.iOrderServicePort = iOrderServicePort;
        this.iOrderRequestMapper = iOrderRequestMapper;
        this.iOrderResponseMapper = iOrderResponseMapper;
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.iPlatePersistencePort = iPlatePersistencePort;
        this.iPlateServicePort = iPlateServicePort;
        this.iPlateResponseMapper = iPlateResponseMapper;
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.twilioConfiguration = twilioConfiguration;
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
        order.setIdEmployee(null);
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
    public List<OrderGeneralResponse> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest) {
//        List<OrderGeneralResponse> orders = iOrderResponseMapper.toOrderResponseList(iOrderServicePort.getAllOrdersByState(page, size, ordersStateRequest));
        Long restaurantOfTheEmployee = iEmployeeServicePort.getEmployee(ordersStateRequest.getIdEmployee()).getIdRestaurant();
//
//        List<OrderGeneralResponse> ordersByRestaurantId = iOrderServicePort.getOrdersByRestaurantId()

        return iOrderResponseMapper.toOrderResponseList(iOrderServicePort.getAllOrdersByState(page, size, ordersStateRequest, restaurantOfTheEmployee));

//        List<OrderGeneralResponse> orderResponses = new ArrayList<>();
//
//        for (int i = 0; i < orders.size(); i++) {
//            List<OrderPlates> orderPlates = getOrderPlatesById(orders.get(i).getId());
//            OrderGeneralResponse orderResponse = new OrderGeneralResponse();
//            orderResponse.setId(orders.get(i).getId());
//            orderResponse.setDate(orders.get(i).getDate());
//            orderResponse.setIdClient(orders.get(i).getIdClient());
//            orderResponse.setOrderPlatesResponse(new ArrayList<>());
//            for (int j = 0; j < orderPlates.size(); j++) {
//                OrderPlatesResponse orderPlateResponse = iPlateResponseMapper.toOrderPlatesResponse(orderPlates.get(j).getPlate());
//                orderPlateResponse.setAmount(orderPlates.get(j).getAmount());
//                orderResponse.getOrderPlatesResponse().add(orderPlateResponse);
//            }
//            orderResponses.add(orderResponse);
//        }
//        return orderResponses;

    }

   @Override
    public List<OrderPlates> getOrderPlatesById(Long id) {
        return iOrderPlatesServicePort.getAllOrderPlatesByOrderId(id);
    }

    @Override
    public void updateOrderToAsignIt(OrderUpdateRequest orderUpdateRequest) {
        List<Order> ordersUpdated = new ArrayList<>();
        for(Long idOrder: orderUpdateRequest.getIdOrders()) {
            Order order = iOrderServicePort.getOrderById(idOrder);
            order.setState("Preparing");
            order.setIdEmployee(orderUpdateRequest.getIdEmployee());

            ordersUpdated.add(order);
        }

        iOrderServicePort.updateOrderToAsignIt(ordersUpdated);
    }

    @Override
    public void notifyOrderIsReady(OrderIsReadyRequest orderIsReadyRequest) {
        Order order = iOrderServicePort.getOrderByIdClient(orderIsReadyRequest.getIdClient());
        order.setState("Ready");

        iOrderServicePort.saveOrder(order);

        Double randomPin = Math.random() * (10000 - orderIsReadyRequest.getIdClient()+1);

        String bodySms = "\n\nHi, your order is finished.\n\nRemember to indicate this PIN number to take your order:\n\n"+Math.round(randomPin);

        //Sending message to client: order is ready
        twilioConfiguration.sendSMS(bodySms);

        //Saving the idClient and the pin into the global variable clientList
        UsersPin userPinList = new UsersPin();

        userPinList.setIdClient(orderIsReadyRequest.getIdClient());
        userPinList.setPin(Math.round(randomPin));


        JSONObject json = new JSONObject();
        json.put("idClient", userPinList.getIdClient());
        json.put("pin", userPinList.getPin());


        if(UsersPin.clientList == null) {
            UsersPin.clientList = new HashMap<>();
        }

        // Adding the client package into the Json list, converting JsonObject to HashMap
        UsersPin.clientList.put(userPinList.getIdClient(), new Gson().fromJson(json.toString(), HashMap.class));
        System.out.println("JSON:\n\n"+UsersPin.clientList);

    }


}
