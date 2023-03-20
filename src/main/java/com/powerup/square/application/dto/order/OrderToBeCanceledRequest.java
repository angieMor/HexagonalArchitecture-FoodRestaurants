package com.powerup.square.application.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderToBeCanceledRequest {

    @NotNull(message = "Field 'idClient' can't be null")
    private Long idClient;

}
