package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.Restaurant;
import com.powerup.square.infraestructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantMapper {
    @Mappings({
            @Mapping(target="name", source="name"),
            @Mapping(target="address", source="address"),
            @Mapping(target="idOwner", source="idOwner"),
            @Mapping(target="phone", source="phone"),
            @Mapping(target="urlLogo", source="urlLogo"),
            @Mapping(target="nit", source="nit")
    })
    RestaurantEntity toEntity(Restaurant restaurant);
    Restaurant toRestaurant(RestaurantEntity restaurantEntity);
}
