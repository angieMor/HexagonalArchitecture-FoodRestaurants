package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.order.*;
import com.powerup.square.application.dto.user.UsersPin;
import com.powerup.square.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaveOrderHandlerDataTest {

    public static Order obtainOrder(){
        return new Order(
                1L,
                44L,
                new Date(),
                "Pending",
                new Restaurant(
                        1L,
                        "Diegos pizza",
                        "Street 24",
                        4L,
                        "+573013265989",
                        "https://www.logo.com",
                        "315648791"
                ),
                null
        );
    }

    public static OrderPlates obtainOrderPlates(){
        return new OrderPlates(
                new Order(
                        1L,
                        44L,
                        new Date(),
                        "Pending",
                        new Restaurant(
                                1L,
                                "Diegos pizza",
                                "Street 24",
                                4L,
                                "+573013265989",
                                "https://www.logo.com",
                                "315648791"
                        ),
                        null
                ),
                new Plate(
                        2L,
                        "Mexican Hamburguer",
                        new Category(
                                1L,
                                "Hamburger",
                                "Has 2 breads, tomato, lettuce, bacon and mozzarella cheese"
                        ),
                        "Have nachos, sour cream, guacamole and pico de gallo",
                        15L,
                        new Restaurant(
                                1L,
                                "Angus Hamburguers",
                                "Street 25",
                                10L,
                                "3013218520",
                                "www.logo.es",
                                "ASD-121854-YU"
                        ),
                        "www.hamburger.com/asdas.png",
                        true
                ),
                1L
        );
    }


    public static OrderGeneralRequest ObtainOrderGeneralRequest(){
       OrderGeneralRequest orderGeneralRequest = new OrderGeneralRequest();

       orderGeneralRequest.setIdClient(44L);

       List<Long> idPlates = new ArrayList<>();
       idPlates.add(1L);
       idPlates.add(2L);
       orderGeneralRequest.setIdPlates(idPlates);

       List<Long> amountPlates = new ArrayList<>();
       amountPlates.add(1L);
       amountPlates.add(1L);
       orderGeneralRequest.setAmountPlates(amountPlates);

       orderGeneralRequest.setIdRestaurant(1L);

        return orderGeneralRequest;
    }

    public static OrdersStateRequest obtainOrdersStaterequest(){
        OrdersStateRequest ordersStateRequest = new OrdersStateRequest();

        ordersStateRequest.setState("Pending");
        ordersStateRequest.setIdEmployee(44L);

        return ordersStateRequest;
    }

    public static OrderUpdateRequest obtainOrderUpdateRequest(){
        OrderUpdateRequest orderUpdateRequest = new OrderUpdateRequest();

        List<Long> idOrders = new ArrayList<>();
        idOrders.add(1L);

        orderUpdateRequest.setIdEmployee(44L);
        orderUpdateRequest.setIdOrders(idOrders);

        return orderUpdateRequest;
    }

    public static OrderIsReadyRequest obtainOrderIsReady(){
        OrderIsReadyRequest orderIsReadyRequest = new OrderIsReadyRequest();

        orderIsReadyRequest.setIdClient(45L);
        orderIsReadyRequest.setPin(1020.0);

        return orderIsReadyRequest;
    }

    public static OrderDeveliveredRequest obtainOrderDeliveredRequest(){
        OrderDeveliveredRequest orderDeveliveredRequest = new OrderDeveliveredRequest();

        orderDeveliveredRequest.setIdClient(5L);
        orderDeveliveredRequest.setPin(2020.0);

        return orderDeveliveredRequest;
    }

    public static UsersPin obtainUsersPin(){

        UsersPin usersPin = new UsersPin();

        usersPin.setIdClient(5L);
        usersPin.setPin(2020L);

        return usersPin;

    }

    public static OrderToBeCanceledRequest obtainOrderToBeCanceledRequest(){
        OrderToBeCanceledRequest orderToBeCanceledRequest = new OrderToBeCanceledRequest();

        orderToBeCanceledRequest.setIdClient(5L);

        return orderToBeCanceledRequest;
    }

}
