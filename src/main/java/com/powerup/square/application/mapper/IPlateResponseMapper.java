package com.powerup.square.application.mapper;

import com.powerup.square.application.dto.order.OrderPlatesResponse;
import com.powerup.square.application.dto.plate.PlateResponse;
import com.powerup.square.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateResponseMapper {
//    @Mappings({
//            @Mapping(target="id", source="id"),
//            @Mapping(target="name", source="name"),
//            @Mapping(target="category", source="category"),
//            @Mapping(target="description", source="description"),
//            @Mapping(target="price", source="price"),
//            @Mapping(target="urlImage", source="urlImage")
//    })
    PlateResponse toPlateResponse(Plate plate);

    OrderPlatesResponse toOrderPlatesResponse(Plate plate);

    List<PlateResponse> toPlateResponseList(List<Plate> plate);

}
