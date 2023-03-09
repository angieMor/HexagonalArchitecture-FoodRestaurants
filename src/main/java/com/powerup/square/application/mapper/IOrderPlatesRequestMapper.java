package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.OrderGeneralRequest;
import com.powerup.square.domain.model.OrderPlates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderPlatesRequestMapper {
//    @Mappings({
//            @Mapping(target="idClient", source="idClient"),
//            @Mapping(target="idPlates", source="idPlates"),
//            @Mapping(target="amountPlates", source="amountPlates"),
//            @Mapping(target="idRestaurant", source="idRestaurant")
//    })

    OrderPlates toOrderPlates(OrderGeneralRequest orderGeneralRequest);

}
