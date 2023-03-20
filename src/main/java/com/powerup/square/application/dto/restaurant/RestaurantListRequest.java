package com.powerup.square.application.dto.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantListRequest {

    @NotNull(message = "Field 'amount' can't be null")
    private Long amount;

    @NotNull(message = "Field 'page' can't be null")
    @NotNull
    private Long page;

}
