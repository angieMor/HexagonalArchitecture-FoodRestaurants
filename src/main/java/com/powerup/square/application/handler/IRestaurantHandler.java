package com.powerup.square.application.handler;

import com.powerup.square.application.dto.RestaurantListRequest;
import com.powerup.square.application.dto.RestaurantListResponse;
import com.powerup.square.application.dto.RestaurantResponse;
import com.powerup.square.application.dto.RestaurantRequest;

import java.util.List;

public interface IRestaurantHandler {
    void saveRestaurant(RestaurantRequest restaurantRequest);
    RestaurantResponse getRestaurant(Long id);

    List<RestaurantResponse> getAllRestaurant(RestaurantListRequest restaurantListRequest);
}
