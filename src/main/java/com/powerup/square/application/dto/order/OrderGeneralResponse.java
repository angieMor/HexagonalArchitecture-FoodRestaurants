package com.powerup.square.application.dto.order;

import com.powerup.square.domain.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderGeneralResponse {

    private Long id;
    private Date date;
    private Long idClient;

    private String state;

//    private List<OrderPlatesResponse> orderPlatesResponse;

}
