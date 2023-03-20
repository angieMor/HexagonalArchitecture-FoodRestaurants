package com.powerup.square.application.dto.employee;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EmployeeRequest {
    @NotNull(message = "Field 'idUser' can't be null")
    private Long idUser;
    @NotNull(message = "Field 'idRestaurant' can't be null")
    private Long idRestaurant;
    @NotBlank(message = "Field 'idUser' can't be null")
    private String field;

}
