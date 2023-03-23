package com.powerup.square.infraestructure.configuration.security;

import com.google.gson.Gson;
import com.powerup.square.application.dto.order.*;
import com.powerup.square.application.dto.plate.PlateIsActiveRequest;
import com.powerup.square.application.dto.plate.PlateListRequest;
import com.powerup.square.application.dto.plate.PlateRequest;
import com.powerup.square.application.dto.plate.PlateUpdatingRequest;
import com.powerup.square.application.dto.restaurant.RestaurantListRequest;
import com.powerup.square.application.dto.user.UserRequest;
import com.powerup.square.application.handler.impl.SaveOrderHandlerDataTest;
import com.powerup.square.application.handler.impl.SavePlateHandlerDataTest;
import com.powerup.square.application.handler.impl.SaveRestaurantHandlerDataTest;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    //                  Restaurant tests
    @Test
    @WithMockUser(username = "Admin", roles = "ADMIN")
    void proprietaryCreatingRestaurant() throws Exception{
        Restaurant restaurant = SaveRestaurantHandlerDataTest.obtainRestaurant();

        Gson gson = new Gson();
        String json = gson.toJson(restaurant);


        mockMvc.perform(post("/restaurants/createRestaurant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "Client")
    void clientGettingAllRestaurants() throws Exception{
        RestaurantListRequest restaurantListRequest = SaveRestaurantHandlerDataTest.obtainRestaurantListRequest();

        Gson gson = new Gson();
        String json = gson.toJson(restaurantListRequest);


        mockMvc.perform(post("/restaurants/allRestaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    //                  Plates tests

    @Test
    @WithMockUser(username = "Proprietary", roles = "PROPRIETARY")
    void proprietaryCreatingPlate() throws Exception{
        PlateRequest plateRequest = SavePlateHandlerDataTest.obtainPlateRequest();

        Gson gson = new Gson();
        String json = gson.toJson(plateRequest);


        mockMvc.perform(post("/plates/createPlate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "Proprietary", roles = "PROPRIETARY")
    void proprietaryUpdatingPlate() throws Exception{
        PlateUpdatingRequest plateUpdatingRequest = SavePlateHandlerDataTest.obtainPlateUpdatingRequest();

        Gson gson = new Gson();
        String json = gson.toJson(plateUpdatingRequest);


        mockMvc.perform(put("/plates/putPlate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Proprietary", roles = "PROPRIETARY")
    void proprietaryUpdateActiveStatusPlate() throws Exception{
        PlateIsActiveRequest plateIsActiveRequest = SavePlateHandlerDataTest.obtainPlateIsActiveRequest();

        Gson gson = new Gson();
        String json = gson.toJson(plateIsActiveRequest);


        mockMvc.perform(put("/plates/putActivate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Client")
    void clientGetAllPlates() throws Exception{
        PlateListRequest plateListRequest = SavePlateHandlerDataTest.obtainPlateListRequest();

        Gson gson = new Gson();
        String json = gson.toJson(plateListRequest);


        mockMvc.perform(post("/plates/allPlates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Client")
    void clientCreateOrder() throws Exception{
        OrderGeneralRequest orderGeneralRequest = SaveOrderHandlerDataTest.ObtainOrderGeneralRequest();

        Gson gson = new Gson();
        String json = gson.toJson(orderGeneralRequest);


        mockMvc.perform(post("/orders/createOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "Employee", roles = "EMPLOYEE")
    void employeeFilterOrdersByState() throws Exception{
        OrdersStateRequest ordersStateRequest = SaveOrderHandlerDataTest.obtainOrdersStaterequest();

        Gson gson = new Gson();
        String json = gson.toJson(ordersStateRequest);


        mockMvc.perform(post("/orders/ordersByState")
                        .param("page", "0")
                        .param("size", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(username = "Employee", roles = "EMPLOYEE")
//    void employeeAsignAnOrderToHimself() throws Exception{
//        OrderUpdateRequest orderUpdateRequest = SaveOrderHandlerDataTest.obtainOrderUpdateRequest();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(orderUpdateRequest);
//
//
//        mockMvc.perform(post("/orders/asignOrder")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "Employee", roles = "EMPLOYEE")
    void employeeNotifyThatTheOrderIsReady() throws Exception{
        OrderIsReadyRequest orderIsReadyRequest = SaveOrderHandlerDataTest.obtainOrderIsReady();

        Gson gson = new Gson();
        String json = gson.toJson(orderIsReadyRequest);


        mockMvc.perform(post("/orders/notifyOrderIsReady")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

//    @Test
//    @WithMockUser(username = "Employee", roles = "EMPLOYEE")
//    void employeeSetOrderToDelivered() throws Exception{
//        OrderDeveliveredRequest orderDeveliveredRequest = SaveOrderHandlerDataTest.obtainOrderDeliveredRequest();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(orderDeveliveredRequest);
//
//
//        mockMvc.perform(post("/orders/setOrderToDelivered")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "Client")
    void clientCancelsOrder() throws Exception{
        OrderToBeCanceledRequest orderToBeCanceledRequest = SaveOrderHandlerDataTest.obtainOrderToBeCanceledRequest();

        Gson gson = new Gson();
        String json = gson.toJson(orderToBeCanceledRequest);


        mockMvc.perform(post("/orders/cancelOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}