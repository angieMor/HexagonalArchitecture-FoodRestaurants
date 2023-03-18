package com.powerup.square.application.dto.order;

import com.powerup.square.domain.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderUpdateRequest {

    private Long idEmployee;

    private List<Long> IdOrders;

}
