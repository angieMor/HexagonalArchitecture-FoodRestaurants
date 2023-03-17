package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.order.OrderGeneralRequest;
import com.powerup.square.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderRequestMapper {

    Order toOrder(OrderGeneralRequest orderGeneralRequest);

}
