package com.powerup.square.application.dto.order;

import com.powerup.square.domain.model.Order;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class OrderUpdateRequest {

    @NotNull(message = "Field 'idEmployee' can't be null")
    private Long idEmployee;
    @NotNull(message = "Field List 'idOrders' can't be null")
    private List<Long> IdOrders;

}
