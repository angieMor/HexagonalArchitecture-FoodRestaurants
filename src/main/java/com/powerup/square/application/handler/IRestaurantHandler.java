package com.powerup.square.application.handler;

import com.powerup.square.application.dto.RestaurantResponse;
import com.powerup.square.application.dto.RestaurantRequest;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse getRestaurant(Long id);
}
