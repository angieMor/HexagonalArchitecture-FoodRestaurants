package com.powerup.square.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderGeneralResponse {

    private List<Long> idPlates;
    private List<Long> amountPlates;
    private Long idRestaurant;

}
