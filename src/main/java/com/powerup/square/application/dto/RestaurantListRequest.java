package com.powerup.square.application.dto;

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
