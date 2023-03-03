package com.powerup.square.application.dto;

import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateResponse {

    private Long id;
    private String name;
    private Category category;
    private String description;
    private Long price;
    private Restaurant restaurant;
    private String urlImage;

}
