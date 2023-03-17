package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateListRequest {

    private Long page;

    private Long amount;

    private Long idRestaurant;

}
