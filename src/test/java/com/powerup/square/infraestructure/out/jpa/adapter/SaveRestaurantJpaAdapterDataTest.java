package com.powerup.square.infraestructure.out.jpa.adapter;

import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;

public class SaveRestaurantJpaAdapterDataTest {

    public static Restaurant obtainRestaurant(){

        Restaurant restaurant = new Restaurant(
                100L,
                "Angus Hamburguers",
                "Street 25",
                10L,
                "3013218520",
                "www.logo.es",
                "12315678979"
        );

        return restaurant;

    }

    public static RestaurantEntity obtainRestaurantEntity(){
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setId(1L);
        restaurantEntity.setName("Angus Hamburguers");
        restaurantEntity.setAddress("Street 25");
        restaurantEntity.setIdOwner(10L);
        restaurantEntity.setPhone("3013218520");
        restaurantEntity.setUrlLogo("www.logo.es");
        restaurantEntity.setNit("31534564");

        return restaurantEntity;


    }

}
