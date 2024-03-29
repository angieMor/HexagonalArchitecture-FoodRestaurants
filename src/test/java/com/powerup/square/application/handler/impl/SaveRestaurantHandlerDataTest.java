package com.powerup.square.application.handler.impl;

import com.powerup.square.application.dto.restaurant.RestaurantListRequest;
import com.powerup.square.application.dto.restaurant.RestaurantRequest;
import com.powerup.square.domain.model.Restaurant;

public class SaveRestaurantHandlerDataTest {

    public static Restaurant obtainRestaurant(){

        Restaurant restaurant = new Restaurant(
                100L,
                "Angus Hamburguers",
                "Street 25",
                10L,
                "3013218520",
                "https://www.logo.es",
                "12315678979"
        );

        return restaurant;

    }

    public static RestaurantRequest obtainRestaurantRequest(){
        RestaurantRequest restaurantRequest = new RestaurantRequest();

        restaurantRequest.setName("Angus Hamburguers");
        restaurantRequest.setAddress("Street 25");
        restaurantRequest.setIdOwner(10L);
        restaurantRequest.setPhone("3013218520");
        restaurantRequest.setUrlLogo("www.logo.es");
        restaurantRequest.setNit("31534564");

        return restaurantRequest;
    }

    public static RestaurantListRequest obtainRestaurantListRequest(){
        RestaurantListRequest restaurantListRequest = new RestaurantListRequest();

        restaurantListRequest.setAmount(3L);
        restaurantListRequest.setPage(0L);

        return restaurantListRequest;
    }

}
