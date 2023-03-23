package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlateUpdatingRequest {
    private Long id;
    @NotBlank(message = "Field 'description' can't be blank")
    private String description;


    @NotNull(message = "Field 'price' can't be blank")
    private Long price;
//    @NotNull(message = "Field 'idOwner' can't be null")
//    private Long idOwner;
}
