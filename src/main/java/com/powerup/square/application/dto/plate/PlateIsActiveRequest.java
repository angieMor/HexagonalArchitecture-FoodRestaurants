package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlateIsActiveRequest {

    @NotNull(message = "Field 'id' can't be null")
    private Long id;
    @NotNull(message = "Field Boolean 'active' can't be null")
    private Boolean active;

}
