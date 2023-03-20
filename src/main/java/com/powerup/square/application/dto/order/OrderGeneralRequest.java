package com.powerup.square.application.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class OrderGeneralRequest {

    @NotNull(message =  "Field 'idClient' can't be null")
    private Long idClient;
    @NotNull(message =  "Field List 'idPlates' can't be null")
    private List<Long> idPlates;
    @NotNull(message =  "Field List 'amountPlates' can't be null")
    private List<Long> amountPlates;
    @NotNull(message =  "Field 'idRestaurant' can't be null")
    private Long idRestaurant;

}
