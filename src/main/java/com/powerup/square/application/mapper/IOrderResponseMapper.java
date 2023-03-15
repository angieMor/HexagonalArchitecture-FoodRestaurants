package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.OrderGeneralResponse;
import com.powerup.square.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderResponseMapper {

//    @Mappings({
//            @Mapping(target="id", source="id"),
//            @Mapping(target="idClient", source="idClient"),
//            @Mapping(target="date", source="date"),
//            @Mapping(target="state", source="state")
//    })

    OrderGeneralResponse toOrderResponse(Order order);
    List<OrderGeneralResponse> toOrderResponseList(List<Order> order);

}
