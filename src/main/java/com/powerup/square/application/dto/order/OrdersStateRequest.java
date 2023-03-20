package com.powerup.square.application.dto.order;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrdersStateRequest {

    @NotBlank(message = "Field 'state' can't be blank")
    private String state;

    @NotNull(message = "Field 'idEmployee' can't be null")
    private Long idEmployee;

}
