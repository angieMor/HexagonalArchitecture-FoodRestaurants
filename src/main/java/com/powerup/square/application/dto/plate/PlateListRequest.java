package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlateListRequest {

    @NotNull(message = "Field 'page' can't be null")
    private Long page;
    @NotNull(message = "Field 'amount' can't be null")
    private Long amount;
    @NotNull(message = "Field 'idRestaurant' can't be null")
    private Long idRestaurant;

}
