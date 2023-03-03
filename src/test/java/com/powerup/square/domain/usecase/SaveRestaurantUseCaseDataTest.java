package com.powerup.square.domain.usecase;

import com.powerup.square.domain.model.Restaurant;

public class SaveRestaurantUseCaseDataTest {

    public static Restaurant obtainRestaurant() {
        Restaurant restaurant = new Restaurant(
                2L,
                "Angus Hamburguer",
                "Street 50",
                22L,
                "3013544379",
                "https://www.elcolombiano.com/asds.png",
                "1324564887"
        );

        return restaurant;
    }

}
