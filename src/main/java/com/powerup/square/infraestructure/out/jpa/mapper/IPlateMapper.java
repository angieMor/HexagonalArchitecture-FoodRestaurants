package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.Plate;
import com.powerup.square.infraestructure.out.jpa.entity.PlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlateMapper {
    @Mappings({
            @Mapping(target="id", source="id"),
            @Mapping(target="name", source="name"),
            @Mapping(target="description", source="description"),
            @Mapping(target="price", source="price"),
            @Mapping(target="urlImage", source="urlImage"),
            @Mapping(target="active", source="active")
    })
    PlateEntity toEntity(Plate plate);
    Plate toPlate(PlateEntity plateEntity);

    List<Plate> toPlate(List<PlateEntity> plateEntity);
}
