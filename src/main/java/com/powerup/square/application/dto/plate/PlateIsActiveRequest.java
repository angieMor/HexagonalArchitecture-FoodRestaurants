package com.powerup.square.application.dto.plate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateIsActiveRequest {

    private Long id;
    private Boolean active;

}
