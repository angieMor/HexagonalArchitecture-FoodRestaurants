package com.powerup.square.application.dto.restaurant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestaurantListRequest {

    @NotNull
    private Long amount;

    @NotNull
    private Long page;

}
