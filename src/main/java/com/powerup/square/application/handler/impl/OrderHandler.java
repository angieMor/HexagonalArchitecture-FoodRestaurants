package com.powerup.square.application.handler.impl;


import com.google.gson.Gson;
import com.powerup.square.application.dto.order.*;
import com.powerup.square.application.dto.user.UsersPin;
import com.powerup.square.application.handler.IOrderHandler;
import com.powerup.square.application.mapper.IOrderRequestMapper;
import com.powerup.square.application.mapper.IOrderResponseMapper;
import com.powerup.square.domain.api.*;
import com.powerup.square.domain.exception.*;
import com.powerup.square.domain.model.Order;
import com.powerup.square.domain.model.OrderPlates;
import com.powerup.square.domain.spi.IPlatePersistencePort;
import com.powerup.square.infraestructure.configuration.TwilioConfiguration;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    private final IEmployeeServicePort iEmployeeServicePort;

    private final TwilioConfiguration twilioConfiguration;


    public OrderHandler(IOrderServicePort iOrderServicePort, IOrderRequestMapper iOrderRequestMapper, IOrderResponseMapper iOrderResponseMapper, IOrderPlatesServicePort iOrderPlatesServicePort, IRestaurantServicePort iRestaurantServicePort, IPlatePersistencePort iPlatePersistencePort, IPlateServicePort iPlateServicePort, IEmployeeServicePort iEmployeeServicePort, TwilioConfiguration twilioConfiguration) {
        this.iOrderServicePort = iOrderServicePort;
        this.iOrderRequestMapper = iOrderRequestMapper;
        this.iOrderResponseMapper = iOrderResponseMapper;
        this.iOrderPlatesServicePort = iOrderPlatesServicePort;
        this.iRestaurantServicePort = iRestaurantServicePort;
        this.iPlatePersistencePort = iPlatePersistencePort;
        this.iPlateServicePort = iPlateServicePort;
        this.iEmployeeServicePort = iEmployeeServicePort;
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void saveOrder(OrderGeneralRequest orderGeneralRequest) {
        Order order = iOrderRequestMapper.toOrder(orderGeneralRequest);

        // Validates if client did an order before
        if(iOrderServicePort.existsByIdClient(order.getIdClient())) {
            String orderServicePort = iOrderServicePort.getOrderByIdClient(order.getIdClient()).getState();
            // If state is Ready|Pending|Preparing, it won't let do the order
            if(orderServicePort
                    .equals("Ready") ||
                    orderServicePort
                            .equals("Pending") ||
                    orderServicePort
                            .equals("Preparing"))
            {
                throw new PendingOrderAlreadyExistsException();
            }
        }

        // Validates if plateId requested is from the restaurant given
        for(int x = 0; x<=orderGeneralRequest.getIdPlates().size()-1;x++) {
            if(!iPlateServicePort.getPlate(orderGeneralRequest.getIdPlates().get(x)).getRestaurant().getId()
                    .equals(orderGeneralRequest.getIdRestaurant())) {
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

        //Saving data of the plates in order_plates table
        iOrderPlatesServicePort.saveOrderPlates(listOrderPlates);
    }

    @Override
    public List<OrderGeneralResponse> getAllOrdersByState(int page, int size, OrdersStateRequest ordersStateRequest) {

        Long restaurantOfTheEmployee = iEmployeeServicePort.getEmployee(ordersStateRequest.getIdEmployee()).getIdRestaurant();
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

        //Employee can assign himself more than 1 order
        List<Order> ordersUpdated = new ArrayList<>();
        for(Long idOrder: orderUpdateRequest.getIdOrders()) {
            Order order = iOrderServicePort.getOrderById(idOrder);

            //If an order was already assigned, the process will fail
            if(order.getState().equals("Preparing")){
                throw new OrderAssignedAlreadyException();
            }
            order.setState("Preparing");
            order.setIdEmployee(orderUpdateRequest.getIdEmployee());

            ordersUpdated.add(order);
        }

        iOrderServicePort.updateOrderToAsignIt(ordersUpdated);
    }

    @Override
    public void notifyOrderIsReady(OrderIsReadyRequest orderIsReadyRequest) {
        // Order exists
        if(!iOrderServicePort.existsByIdClient(orderIsReadyRequest.getIdClient())){
            throw new OrderDoNotExistsException();
        }

        // Client should be notified only when order is on state Preparing
        if(!iOrderServicePort.getOrderByIdClient(orderIsReadyRequest.getIdClient()).getState()
                .equals("Preparing"))
        {
            throw new OrderStateDeliveredException();
        }

        Order order = iOrderServicePort.getOrderByIdClient(orderIsReadyRequest.getIdClient());
        order.setState("Ready");

        iOrderServicePort.saveOrder(order);

        double randomPin = Math.random() * (10000 - orderIsReadyRequest.getIdClient()+1);

        String bodySms = "\n\nHi, your order is finished.\n\nRemember to indicate this PIN number to take your order:\n\n"+Math.round(randomPin);

        //Send message to the client with the pin to give to the employee
        twilioConfiguration.sendSMS(bodySms);

        /*Saving the idClient and the pin into the global variable clientList, this will be reseted each time that
            the program run again*/
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
    }

    @Override
    public void setOrderToDelivered(OrderDeveliveredRequest orderDeveliveredRequest) {
        // Order exists
        if(!iOrderServicePort.existsByIdClient(orderDeveliveredRequest.getIdClient())){
            throw new OrderDoNotExistsException();
        }

        Order order  = iOrderServicePort.getOrderByIdClient(orderDeveliveredRequest.getIdClient());
        //Only Ready orders are allowed to be in Delivered status
        if(!order.getState()
                .equals("Ready"))
        {
            throw new OrderIsNotReadyException();
        }

        Set<Long> keys = UsersPin.clientList.keySet();
        //Reads the Hashmap content
        for(Iterator<Long> key = keys.iterator(); key.hasNext();){
            Long currentKey = key.next();
            if(currentKey.equals(orderDeveliveredRequest.getIdClient()) &&
                    orderDeveliveredRequest.getPin().equals(UsersPin.clientList.get(currentKey).get("pin")))
            {
                order.setState("Delivered");
                iOrderServicePort.saveOrder(order);
                key.remove();
            } else if(!key.hasNext()){
                    throw new OrderPinGivenIncorrectException();
            }
        }
    }

    @Override
    public void setOrderToCanceled(OrderToBeCanceledRequest orderToBeCanceledRequest) {
        // Order exists
        if(!iOrderServicePort.existsByIdClient(orderToBeCanceledRequest.getIdClient())){
            throw new OrderDoNotExistsException();
        }

        Order order = iOrderServicePort.getOrderByIdClient(orderToBeCanceledRequest.getIdClient());
        //Only pending orders are allowed to be canceled
        if(!order.getState()
                .equals("Pending"))
        {
            String body = "We're sorry, your order was already taken by the restaurant and can't be canceled";
            twilioConfiguration.sendSMS(body);
            throw new OrderIsNotPendingException();
        }
        //Order can be canceled, so it is saved in DB
        order.setState("Canceled");
        iOrderServicePort.saveOrder(order);

        //Sends a notification to user: order canceled successfully
        String body = "Your order was canceled";
        twilioConfiguration.sendSMS(body);
    }
}
