package com.powerup.square.application.handler;

import com.powerup.square.application.dto.restaurant.RestaurantListRequest;
import com.powerup.square.application.dto.restaurant.RestaurantResponse;
import com.powerup.square.application.dto.restaurant.RestaurantRequest;

import java.util.List;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse getRestaurant(Long id);

    List<RestaurantResponse> getAllRestaurant(RestaurantListRequest restaurantListRequest);
}
