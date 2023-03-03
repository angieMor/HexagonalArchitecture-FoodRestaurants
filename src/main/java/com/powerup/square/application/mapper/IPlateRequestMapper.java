package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.PlateRequest;
import com.powerup.square.application.dto.RestaurantRequest;

import com.powerup.square.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateRequestMapper {

    @Mappings({
            @Mapping(target="name", source="name"),
            @Mapping(target="description", source="description"),
            @Mapping(target="price", source="price"),
            @Mapping(target="urlImage", source="urlImage")
    })
    Plate toPlate(PlateRequest plateRequest);

}
