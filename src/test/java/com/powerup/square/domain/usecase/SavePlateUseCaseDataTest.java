package com.powerup.square.domain.usecase;

import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.model.Plate;
import com.powerup.square.domain.model.Restaurant;

public class SavePlateUseCaseDataTest {

    public static Plate obtainPlate(){
        Plate plate = new Plate(
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
                        100L,
                        "Angus Hamburguers",
                        "Street 25",
                        10L,
                        "3013218520",
                        "www.logo.es",
                        "ASD-121854-YU"
                ),
                "www.hamburger.com/asdas.png",
                true
        );

        return plate;
    }
}
