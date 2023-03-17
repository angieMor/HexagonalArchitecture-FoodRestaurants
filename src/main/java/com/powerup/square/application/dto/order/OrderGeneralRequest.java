package com.powerup.square.application.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class OrderGeneralRequest {

    private Long idClient;
    private List<Long> idPlates;
    private List<Long> amountPlates;

//    private HashMap<String, Long> idPlates;
//    private HashMap<String, Long> amountPlates;

//    private List<HashMap<String, Long>> plates;
    private Long employeeId;
    private Long idRestaurant;

}
