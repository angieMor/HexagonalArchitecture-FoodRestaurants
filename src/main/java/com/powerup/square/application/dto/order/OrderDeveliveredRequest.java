package com.powerup.square.application.dto.order;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDeveliveredRequest {

    @NotNull(message =  "Field idClient can't be null")
    private Long idClient;

    @NotNull(message =  "Field pin can't be null")
    private Double pin;

}
