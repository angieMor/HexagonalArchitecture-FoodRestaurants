package com.powerup.square.application.dto;

import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
public class EmployeeRequest {
    private Long idUser;
    private Long idRestaurant;
    private String field;

}
